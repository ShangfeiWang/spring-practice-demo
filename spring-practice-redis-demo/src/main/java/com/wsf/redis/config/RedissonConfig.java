package com.wsf.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wsf
 * @since 20230223
 */
@Configuration
@ConfigurationProperties(prefix = "redisson.redis")
public class RedissonConfig {

    private String server;

    public void setServer(String server) {
        this.server = server;
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + server);
        return Redisson.create(config);
    }

}
