package com.wsf.context.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController implements LastModified {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(String name) {
        System.out.println("hello.....");
        return "hello";
    }


    @Override
    public long getLastModified(HttpServletRequest request) {
        return 0;
    }
}
