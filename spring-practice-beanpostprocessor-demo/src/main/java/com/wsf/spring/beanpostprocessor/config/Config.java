package com.wsf.spring.beanpostprocessor.config;

import com.wsf.spring.beanpostprocessor.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wsf
 * @since 20221027
 */
@Configuration
public class Config {

    @Bean
    public User user() {
        return new User();
    }
}
