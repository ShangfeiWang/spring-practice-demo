package com.wsf.context.service;

import org.springframework.context.annotation.Bean;


public class UserServiceFactory {

    @Bean
    public UserService2 userService2() {
        return new UserService2();
    }
}
