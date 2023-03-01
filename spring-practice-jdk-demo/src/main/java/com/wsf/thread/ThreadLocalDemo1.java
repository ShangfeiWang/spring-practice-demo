package com.wsf.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wsf
 * @since 20230223
 */
public class ThreadLocalDemo1 {

    //private static  ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100000; i++) {

            executorService.submit(() -> {
                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                Random random = new Random();
                threadLocal.set(String.valueOf(random.nextInt()));
                //threadLocal.get();
                //threadLocal = null;

                threadLocal.remove();
            });
        }
        System.gc();
        System.out.println(executorService);
    }

}
