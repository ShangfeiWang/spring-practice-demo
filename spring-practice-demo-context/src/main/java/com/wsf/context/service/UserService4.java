package com.wsf.context.service;

import lombok.Data;

import java.util.List;

@Data
public class UserService4 {

    private List<String> names;

    public UserService4(List<String> stringList) {
        this.names = stringList;
    }

    public UserService4(String hello) {
    }
}
