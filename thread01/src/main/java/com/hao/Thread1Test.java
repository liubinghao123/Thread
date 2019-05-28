package com.hao;

import java.util.concurrent.TimeUnit;

/**
 * Created by Keeper on 2019-05-09
 */
public class Thread1Test {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 线程的状态 NEW -> RUNNABLE -> WAITING -> BLOCKED -> TIMED_WAITING -> TERMINATED
         * 线程start后，初始化状态是，RUNNABLE，如果获取到时间片状态为RUNNING（人为定义，在jstack查看依然为RUNNABLE）
         * 当一个线程获取到时间片（锁），其他线程从Runnable状态变成blocked
         */
        Thread t1 = new Thread(()->{
            while(true){

            }
        });

        t1.setName("thread-test-01");
        t1.start();
    }
}
