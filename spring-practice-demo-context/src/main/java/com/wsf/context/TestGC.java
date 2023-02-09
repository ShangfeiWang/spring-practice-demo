package com.wsf.context;

import lombok.Data;

public class TestGC {

    public static void main(String[] args) {
        Stu stu = new Stu();

        System.out.println(stu);

        long l = Runtime.getRuntime().totalMemory();
        System.out.println("total memory = " + l / 1024 / 1024);

        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("total memory = " + maxMemory / 1024 / 1024);

//        try {
//            TimeUnit.SECONDS.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}

@Data
class Stu {
    private String name;

    private int age;
}
