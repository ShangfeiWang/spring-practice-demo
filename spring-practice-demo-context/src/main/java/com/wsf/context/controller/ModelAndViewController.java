package com.wsf.context.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ModelAndViewController {

    @RequestMapping("/modelAndView")
    public String hello2(String name) {
        System.out.println("hello2.....");
        return "page/index";
    }

}
