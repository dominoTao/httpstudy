package com.tao.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultyThreadDemo {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
        int s = 0;
        for (int i = 0; i < 100; i++) {
        Thread thread = new Thread(new TaskDemo());
//            executorService.execute(new TaskDemo());
            thread.start();
            s++;
        }
        System.out.println(s);
//        executorService.shutdown();
    }
}
