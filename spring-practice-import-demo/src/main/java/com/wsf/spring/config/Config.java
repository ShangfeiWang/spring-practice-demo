package com.wsf.spring.config;

import com.wsf.spring.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public User user() {
        return new User();
    }
}
