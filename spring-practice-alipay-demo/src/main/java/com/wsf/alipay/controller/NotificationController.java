package com.wsf.alipay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payNotify")
public class NotificationController {

    @RequestMapping("/notify")
    public void payNotify() {

    }
}
