package com.hao;

import java.util.concurrent.TimeUnit;

/**
 * Created by Keeper on 2019-05-09
 */
public class ThreadInterrupt2Test {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Thread.interrupt()执行jvm的interrupt0 native方法，将变量改为1（默认为0）
         * Thread.interrupted()判断当前线程是为被中断，0：false 1：true 且会将标志位重置（底层变量从1重新设置为0）
         * Thread.isInterrupt()判断当前线程是否被中断，不会重置标志位
         *
         * catch(InterruptException e) 捕获中断异常也会将标志位重置
         * 如果标志位为true，那么执行可中断的代码将会直接抛出中断异常
         */
        Thread t1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("外部异常中断");
                //catch捕获了异常会将标志位重置，这里我们手动将标志位设置为true
                Thread.currentThread().interrupt();
            }
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("标志位已重置");
            } catch (InterruptedException e) {
                System.out.println("标志位未重置");
            }
        });
        t1.start();
        t1.interrupt();

    }
}
