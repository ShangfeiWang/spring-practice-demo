package com.wsf.redis.controller;

import org.redisson.api.RHyperLogLog;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class HypeLogLogController {

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("/testHypeLogLog")
    public Long testHypeLogLog() {
        RHyperLogLog<String> hyperLogLog = redissonClient.getHyperLogLog("wsf:2023");
        for (int i = 0; i < 10000; i++) {
            hyperLogLog.add(UUID.randomUUID().toString());
        }

        return hyperLogLog.count();
    }
}
