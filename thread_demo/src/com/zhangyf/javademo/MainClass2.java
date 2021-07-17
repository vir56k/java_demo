package com.zhangyf.javademo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁和解决示例
 */
class MainApplicatoin {

    static class Desk {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        // 吃饼干
        void eating() throws InterruptedException {
            String name = Thread.currentThread().getName();
            lock1.lock();
            System.out.printf("\t\t [Thread: %s] 开始 eating ... \n", name);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock2.lock();
            // 带有 超时时间的锁定，当超过指定时间就不在等了
             lock2.tryLock(1000, TimeUnit.MILLISECONDS);

            System.out.printf("[Thread: %s] 执行完成 \n", name);
            lock1.unlock();
        }

        // 喝水
        void drinking() {
            String name = Thread.currentThread().getName();
            lock2.lock();
            System.out.printf("[Thread: %s] 开始 drinking ... \n", name);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1.lock();

            System.out.printf("[Thread: %s] 执行完成 \n", name);
            lock2.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Desk desk = new Desk();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    desk.eating();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.setName("thread1");
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                desk.drinking();
            }
        });
        thread2.setName("thread2");
        thread2.start();


//        Thread.sleep(60*1000);
    }

}
