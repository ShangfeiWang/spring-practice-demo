package com.wsf.redis.dao;

import org.springframework.stereotype.Component;

@Component
public class StockDao {

    private long stockCount = 100;

    public long getStock() {
        return stockCount;
    }

    public void updateStock(long stockCount) {
        this.stockCount = stockCount;
    }
}

