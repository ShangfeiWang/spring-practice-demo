package com.wsf.alipay.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String formatDate(LocalDateTime dateTime) {
        return formatter.format(dateTime);
    }


}
