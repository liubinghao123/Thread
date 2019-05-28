package com.hao;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

/**
 * Created by Keeper on 2019-05-09
 */
public class ThreadWaitTest {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread t1 = new Thread(()->{
            synchronized (object){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1获取到时间片");
            }
        });
        Thread t2 = new Thread(()->{
            synchronized (object){
                System.out.println("线程2获取到时间片");
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //唤醒等待队列中的第一个线程，重新进入到竞争队列中参与时间片的争抢
                object.notify();
            }
        });
        t1.start();
        TimeUnit.MICROSECONDS.sleep(10);
        t2.start();
    }
}
