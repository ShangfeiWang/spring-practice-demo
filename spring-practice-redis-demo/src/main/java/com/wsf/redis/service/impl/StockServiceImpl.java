package com.wsf.redis.service.impl;

import com.wsf.redis.dao.StockDao;
import com.wsf.redis.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String stockKey = "stock";

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public Long getStockCount() {
        // 先从缓存中获取，如果缓存中有，则直接从缓存中取到
        String cache = redisTemplate.opsForValue().get(stockKey);
        if (!ObjectUtils.isEmpty(cache)) {
            return Long.valueOf(cache);
        }
        // 如果拿不到，则查询数据库
        RLock lock = redissonClient.getLock("stock:lock");
        if (lock.tryLock()) {
            try {
                // 再次从缓存中获取锁
                cache = redisTemplate.opsForValue().get(stockKey);
                if (!ObjectUtils.isEmpty(cache)) {
                    return Long.valueOf(cache);
                }
                // 查询数据库
                long stock = stockDao.getStock();
                // 为了延时，
                //Thread.sleep(3000);
                redisTemplate.opsForValue().set("stock", String.valueOf(stock));
                return stock;
            } catch (Exception e) {
                log.error("发生异常", e);
            } finally {
                lock.unlock();
            }
        } else {
            log.error("获取锁失败....");
        }

        return 0L;
    }

    public void updateStock(long stockCount) {
        // 更新缓存失败 数据库成功
        // updateCacheFailAndUpdateDbSuccess(stockCount);
        // 更新缓存成功，缓存失败
        // updateCacheSuccessAndUpdateDbSuccess(stockCount);
        // 更新数据库成功，缓存也成功，但是由于网络原因，导致命令执行的先后顺序错误导致了不一致
        // updateDbSuccessAndUpdateCacheSuccess(stockCount);
        // 更新缓存成功，数据库也成功，但是由于网络原因，导致命令执行的先后顺序错误导致了不一致
        // updateCacheSuccessAndUpdateDbSuccess(stockCount);
        // 移除缓存 后 更新数据库
        // removeCacheAfterUpdateDb(stockCount);
        // 先更新数据库 后 移除缓存，这种出现不一致的概率会小一些，但是还是有可能出现缓存不一致 条件比较苛刻
        updateDbAfterRemoveCache(stockCount);
    }


    /**
     * 删-更新-删
     * <p>
     * 先删除的目的是 尽可能的去保证数据的一致行，假如在删除缓存 到 更新数据这个操作时间内，没有其他线程进来，那么更新完成之后其他线程进来是直接能获取到最新的数据
     * 如果是有其他的线程进来了，那此时缓存和数据库也是不一致的，这时就需要使用我们延迟删除缓存的策略来进行补偿
     */
    public void updateDbAndDeleteCacheAfterDeleteCache(long stockCount) {
        log.info("A线程删除缓存数据开始.....");
        redisTemplate.delete(stockKey);
        log.info("A线程删除缓存数据结束.....");
        log.info("A线程更新数据库开始.....");
        stockDao.updateStock(stockCount);
        log.info("A线程更新数据库结束.....");
        // 异步再去删除一次缓存，保证最终一致性
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisTemplate.delete(stockKey);
        }).start();
    }


    /**
     * 先更新数据库，后移除缓存
     * <p>
     * 缓存刚好失效
     * A线程去数据库查询，查询到旧值
     * B线程开始更新数据库
     * B线程开始删除缓存
     * A线程写入缓存
     *
     * @param stockCount 数量
     */
    public void updateDbAfterRemoveCache(long stockCount) {
        log.info("A线程更新数据库开始.....");
        stockDao.updateStock(stockCount);
        log.info("A线程更新数据库结束.....");
        log.info("A线程删除缓存数据开始.....");
        redisTemplate.delete(stockKey);
        log.info("A线程删除缓存数据结束.....");
    }


    /**
     * 先删除缓存，再更新数据库
     * <p>
     * A线程删除缓存
     * B线程查询，缓存查询不到，查DB，查询到的是旧值
     * A线程更新数据库
     * B线程将旧值写入缓存
     *
     * @param stockCount 数量
     */
    public void removeCacheAfterUpdateDb(long stockCount) {
        // A 线程先移除缓存
        log.info("A线程删除缓存数据开始.....");
        redisTemplate.delete(stockKey);
        log.info("A线程删除缓存数据结束.....");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("A线程更新数据库开始.....");
        stockDao.updateStock(stockCount);
        log.info("A线程更新数据库结束.....");
    }

    /**
     * 更新数据库和缓存都成功了，但是由于命令执行的先后可能会导致缓存的不一致
     * A线程更新数据库
     * B线程更新数据库
     * B线程更新缓存
     * A线程更新缓存
     *
     * @param stockCount 数量
     */
    private void updateDbSuccessAndUpdateCacheSuccess(long stockCount) {
        if (stockCount == 6000) {
            // A 线程
            log.info("A线程更新数据库开始.....");
            stockDao.updateStock(stockCount);
            log.info("A线程更新数据库完成.....");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("A线程更新缓存中开始.....");
            redisTemplate.opsForValue().set(stockKey, String.valueOf(stockCount));
            log.info("A线程更新缓存中结束.....");
        } else {
            // B 线程
            log.info("B线程更新数据库开始.....");
            stockDao.updateStock(stockCount);
            log.info("B线程更新数据库完成.....");
            log.info("B线程更新缓存中开始.....");
            redisTemplate.opsForValue().set(stockKey, String.valueOf(stockCount));
            log.info("B线程更新缓存中结束.....");

        }
    }


    /**
     * 更新缓存和数据库都成功了，但是由于命令执行的先后可能会导致缓存的不一致
     * A线程更新缓存
     * B线程更新缓存
     * B线程更新数据库
     * A线程更新数据库
     *
     * @param stockCount 数量
     */
    private void updateCacheSuccessAndUpdateDbSuccess(long stockCount) {
        if (stockCount == 2000) {
            // A 线程
            log.info("A线程更新缓存中开始.....");
            redisTemplate.opsForValue().set(stockKey, String.valueOf(stockCount));
            log.info("A线程更新缓存中结束.....");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("A线程更新数据库开始.....");
            stockDao.updateStock(stockCount);
            log.info("A线程更新数据库完成.....");
        } else {
            // B 线程
            log.info("B线程更新缓存中开始.....");
            redisTemplate.opsForValue().set(stockKey, String.valueOf(stockCount));
            log.info("B线程更新缓存中结束.....");
            log.info("B线程更新数据库开始.....");
            stockDao.updateStock(stockCount);
            log.info("B线程更新数据库完成.....");
        }
    }

    /**
     * 先更新缓存后更新数据库（数据库失败）
     *
     * @param stockCount 数量
     */
    private void updateCacheSuccessAndUpdateDbFail(long stockCount) {
        log.info("更新缓存中开始.....");
        // 先更新缓存，在更新数据库，更新数据库失败
        redisTemplate.opsForValue().set(stockKey, String.valueOf(stockCount));
        int i = 1 / 0;
        stockDao.updateStock(stockCount);
        log.info("更新缓存中结束.....");
    }

    /**
     * 先更新数据库后更新缓存（缓存失败）
     *
     * @param stockCount 数量
     */
    private void updateCacheFailAndUpdateDbSuccess(long stockCount) {
        log.info("更新缓存中开始.....");
        // 先更新数据库，再更新缓存，缓存更新失败
        stockDao.updateStock(stockCount);
        int i = 1 / 0;
        redisTemplate.opsForValue().set(stockKey, String.valueOf(stockCount));
        log.info("更新缓存中结束.....");
    }
}
