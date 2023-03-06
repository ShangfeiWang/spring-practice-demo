package com.wsf.thread;

/**
 * @author wsf
 * @since 20230223
 */
public class ThreadLocalDemo {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        //    ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hello");
        //  threadLocal = null;
        System.gc();

        //System.out.println("main thread value = " + threadLocal.get());
        //new Thread(() -> System.out.println("son thread value = " + threadLocal.get())).start();

        //threadLocal = new ThreadLocal<>();
        threadLocal.set("123");
        //threadLocal = null;
        System.gc();
        System.out.println(System.in);
    }

}
