package com.zhangyf.javademo;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

class MainClass1 {

    static class MyService {
        ReentrantLock reentrantLock = new ReentrantLock(true);

        void doSome() {
            reentrantLock.lock();
            System.out.println(String.format("\t\t\t\t 线程[%s] 执行 ...", Thread.currentThread().getName()));
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        final MyService myService = new MyService();
        Runnable runnable = () -> {
            System.out.println(String.format("线程[%s] 进入 ...", Thread.currentThread().getName()));
            myService.doSome();
        };

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable);
            thread.setName("Thread_" + i);
            threadArray[i] = thread;
        }

        Arrays.stream(threadArray).forEach(Thread::start);


        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
