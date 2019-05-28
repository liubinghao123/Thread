package com.hao;

import java.util.concurrent.TimeUnit;

/**
 * Created by Keeper on 2019-05-09
 */
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Thread.interrupt()执行jvm的interrupt0 native方法，将变量改为1（默认为0）
         * Thread.interrupted()判断当前线程是为被中断，0：false 1：true 且会将标志位重置（底层变量从1重新设置为0）
         * Thread.isInterrupt()判断当前线程是否被中断，不会重置标志位
         *
         * catch(InterruptException e) 捕获中断异常也会将标志位重置
         */
        Thread t1 = new Thread(()->{
//            int i = 0;
//            while(!Thread.currentThread().isInterrupted()){
//                try {
//
//                } catch (InterruptedException e) {
//                    //中断异常被捕获会重置标志位
//                    e.printStackTrace();
//                }
//            }
            System.out.println(Thread.currentThread().isInterrupted());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            System.out.println(Thread.currentThread().isInterrupted());
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        //打印子线程中断前的标志位值
        System.out.println(t1.isInterrupted());
        //中断子线程
        t1.interrupt();

    }
}
