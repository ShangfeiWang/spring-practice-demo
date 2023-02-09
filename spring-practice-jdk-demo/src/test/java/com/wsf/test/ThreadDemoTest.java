package com.wsf.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wsf
 * @since 20230223
 */
@SpringBootTest
public class ThreadDemoTest {

    @Test
    public void testInterrupted() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello");
            }
        });
        thread.start();
        // 3秒后中断线程
        Thread.sleep(3000);
        thread.interrupt();
        Thread.sleep(1000);
        System.out.println("中断操作1s后 判断线程打断状态：" + thread.isInterrupted());

    }

}
