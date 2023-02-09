package com.wsf.context;

import java.util.ArrayList;
import java.util.List;

public class GCTest {

    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            byte[] bytes = new byte[100 * 1024]; // 100kb
            Thread.sleep(100);
            list.add(bytes);
        }
        System.out.println(list);
    }
}