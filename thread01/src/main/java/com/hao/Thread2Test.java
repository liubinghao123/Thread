package com.hao;

import java.util.concurrent.TimeUnit;

/**
 * Created by Keeper on 2019-05-09
 */
public class Thread2Test {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 线程的状态 NEW -> RUNNABLE -> WAITING -> BLOCKED -> TIMED_WAITING -> TERMINATED
         *
         * 当一个线程获取到时间片（锁），其他线程从Runnable状态变成blocked
         */
        Thread t1 = new Thread(()->{
            synchronized (Thread2Test.class){
                System.out.println("I'm thread 01");
                try {
                    TimeUnit.SECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            synchronized (Thread2Test.class){
                System.out.println("I'm thread 02");
                try {
                    TimeUnit.SECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(()->{
            synchronized (Thread2Test.class){
                System.out.println("I'm thread 03");
                try {
                    TimeUnit.SECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setName("thread-test-01");
        t2.setName("thread-test-02");
        t3.setName("thread-test-03");
        t1.start();
        t2.start();
        t3.start();
    }
}
