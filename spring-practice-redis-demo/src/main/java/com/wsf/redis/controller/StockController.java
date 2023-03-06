package com.wsf.redis.controller;

import com.wsf.redis.dao.StockDao;
import com.wsf.redis.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("getStockCount")
    public Long getStockCount() {
        Long stockCount = stockService.getStockCount();
        log.info("redis:stockCount:{} db:stockCount:{}", stockCount, stockDao.getStock());
        return stockCount;
    }


    @RequestMapping("updateStock")
    public void updateStock(@RequestParam Long stockCont) {
        stockService.updateStock(stockCont);
    }

    @RequestMapping("resetCount")
    public void resetCount() {
        stockDao.updateStock(100);
        stringRedisTemplate.delete("stock");
    }

    @RequestMapping("getDbCount")
    public Long getDbCount() {
        return stockDao.getStock();
    }

    @RequestMapping("updateRedisAndDb")
    public void updateRedisAndDb() {
        // A线程
        new Thread(() -> stockService.updateStock(6000)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // B线程
        new Thread(() -> stockService.updateStock(4000)).start();

    }

}
