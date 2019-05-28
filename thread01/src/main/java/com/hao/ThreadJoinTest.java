package com.hao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Keeper on 2019-05-10
 */
public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 线程A调用线程B的join方法，此时线程A会阻塞，线程状态为BLOCKED，只到线程B的生命周期结束后线程A才会重新恢复为RUNNABLE状态
         */
        List<Integer> results = new ArrayList<>();
        Thread t1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <100 ; i++) {
                results.add(i);
            }
        });

        t1.start();
        t1.join();
        System.out.println(results.size());
    }
}
