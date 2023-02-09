package com.wsf.thread;

/**
 * @author wsf
 * @since 20230223
 */
public class ThreadDemo {

    public static void main(String[] args) {
        Thread hello = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello");
        });

        hello.start();
        System.out.println("hello2");
    }

}
