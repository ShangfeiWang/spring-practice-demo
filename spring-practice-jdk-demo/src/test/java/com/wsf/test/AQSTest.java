package com.wsf.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wsf
 * @since 20230223
 */
public class AQSTest {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("1111");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("2222");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t3").start();
    }

}
