package com.tao.test;

import com.tao.util.HTTPClientDemo;

public class TaskDemo implements Runnable {
    @Override
    public void run() {
        HTTPClientDemo.sendPostRequest();
    }
}
