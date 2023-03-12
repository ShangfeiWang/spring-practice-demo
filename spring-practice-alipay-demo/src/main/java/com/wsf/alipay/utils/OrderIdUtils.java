package com.wsf.alipay.utils;

import java.time.LocalDateTime;

public class OrderIdUtils {


    public static String generateOrderNo() {

        return DateUtils.formatDate(LocalDateTime.now());
    }
}
