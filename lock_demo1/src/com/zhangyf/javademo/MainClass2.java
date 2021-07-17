package com.zhangyf.javademo;



import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

class Demo2 {
}

class MainClass2 {

    public static void main(String[] args) {
        System.out.println(" ready ..." );

        ReentrantLock reentrantLock = new ReentrantLock(); // 默认 "不公平锁"
        boolean isFair = true; // 是否 公平锁
        ReentrantLock reentrantLock2 = new ReentrantLock(isFair); // 这个构造方法可以产出 "公平锁"

    }
}
