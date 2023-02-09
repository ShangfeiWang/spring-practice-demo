package com.wsf.test;

import lombok.SneakyThrows;

/**
 * @author wsf
 * @since 20230223
 */

public class VolatileDemoTest {

    private static boolean ready = false;

    public static class MyThread extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("MyThread is running....");
            while (!ready) {
                Thread.sleep(500);
                System.out.println("running...");
            }
            System.out.println("MyThread is end");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new MyThread().start();

        Thread.sleep(1000);
        ready = true;
        System.out.println("ready is =" + ready);
        Thread.sleep(5000);
        System.out.println("main Thread is end");
    }

}
