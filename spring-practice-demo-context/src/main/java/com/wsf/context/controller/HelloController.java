package com.wsf.context.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

@RestController
public class HelloController implements LastModified {

    @Autowired
    private RedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(String name) {
        System.out.println("hello.....");
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "/testRequestParam", method = RequestMethod.GET)
    public String testRequestParam(@RequestParam @NotEmpty String name) {
        System.out.println("hello.....");
        return "hello";
    }

    @Override
    public long getLastModified(HttpServletRequest request) {
        return 0;
    }
}
