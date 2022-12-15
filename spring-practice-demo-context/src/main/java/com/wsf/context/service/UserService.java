package com.wsf.context.service;

import lombok.Data;

@Data
public class UserService {

    private String name;

    private String age;

    private Teacher teacher3;

    public void init() {
        System.out.println("init....");
    }

    public void destroy() {
        System.out.println("destroy...");
    }
}
