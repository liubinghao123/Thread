package com.hao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Keeper on 2019-05-21
 */
public class ReentrantReadWriteLockTest {
    private static Map<String,Object> map = new HashMap<>();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock writeLock = reentrantReadWriteLock.writeLock();
    static Lock readLock = reentrantReadWriteLock.readLock();

    public static void main(String[] args) {


    }

    public static void  put(String key ,Object value){
        try {
            writeLock.lock();
            map.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }

    public static Object get(String key){
        try {
            readLock.lock();
            return map.get(key);
        }finally {
            readLock.unlock();
        }

    }

}
