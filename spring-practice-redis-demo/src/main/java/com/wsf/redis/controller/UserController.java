package com.wsf.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(redisTemplate.opsForValue().get("111"));
        return "hello";
    }

    /**
     * 测试获取不到锁就失败
     *
     * @return 结果
     */
    @RequestMapping("/hello2")
    public String hello2() {
        RLock lock = redissonClient.getLock("lock:order");
        if (lock.tryLock()) {
            try {
                log.info(Thread.currentThread().getName() + "获取到锁了");
                Thread.sleep(2000);
            } catch (Exception e) {
                log.error("发生了异常");
            } finally {
                log.info(Thread.currentThread().getName() + "释放锁");
                lock.unlock();
            }
        } else {
            log.info(Thread.currentThread().getName() + "获取锁失败");
        }
        return "hello";
    }

    /**
     * 测试获取不到锁就等待
     *
     * @return 结果
     */
    @RequestMapping("/hello3")
    public String hello3() throws InterruptedException {
        RLock lock = redissonClient.getLock("lock:order");
        // 第一个参数是等待几秒获取不到锁就失败，如果等待的过程中获取到了锁就执行
        if (lock.tryLock(5, TimeUnit.SECONDS)) {
            try {
                log.info(Thread.currentThread().getName() + "获取到锁了");
                Thread.sleep(3000);
            } catch (Exception e) {
                log.error("发生了异常");
            } finally {
                log.info(Thread.currentThread().getName() + "释放锁");
                lock.unlock();
            }
        } else {
            log.info(Thread.currentThread().getName() + "获取锁失败");
        }
        return "hello";
    }

    /**
     * 测试看门狗的机制
     *
     * @return 结果
     * @throws InterruptedException
     */
    @RequestMapping("/hello4")
    public String hello4() throws InterruptedException {
        RLock lock = redissonClient.getLock("lock:order");
        // 第一个参数是等待几秒获取不到锁就失败，如果等待的过程中获取到了锁就执行
        if (lock.tryLock(5, TimeUnit.SECONDS)) {
            try {
                log.info(Thread.currentThread().getName() + "获取到锁了");
                Thread.sleep(15000);
            } catch (Exception e) {
                log.error("发生了异常");
            } finally {
                log.info(Thread.currentThread().getName() + "释放锁");
                lock.unlock();
            }
        } else {
            log.info(Thread.currentThread().getName() + "获取锁失败");
        }
        return "hello";
    }
}
