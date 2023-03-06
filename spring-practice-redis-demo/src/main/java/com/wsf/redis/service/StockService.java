package com.wsf.redis.service;

public interface StockService {

    Long getStockCount();

    void updateStock(long stockCount);
}
