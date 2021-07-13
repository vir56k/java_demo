package com.zhangyf.javademo;

import java.time.*;

/**
 *
 */
public class NewDateDemo {
    public static void main(String[] args) throws InterruptedException {
        // 获得 系统的默认时区对应的 时钟对象
        Clock clock = Clock.systemDefaultZone();
        // 获得毫秒值
        long millis = clock.millis();
        System.out.println("millis: " + millis);

        // 本地时间
        LocalTime now1 = LocalTime.now();
        System.out.println("now1: " + now1); // 23:34:46.112

        // 本地时间
        LocalDate now2 = LocalDate.now();
        System.out.println("now2: " + now2); // 2021-07-13

        // 本地时间
        LocalDateTime now3 = LocalDateTime.now();
        System.out.println("now2: " + now3); // 2021-07-13T23:34:46.113

        // 持续时间
        LocalDateTime start = LocalDateTime.now();
        Thread.sleep(1234);
        LocalDateTime end = LocalDateTime.now();
        // 两个时间之间的差
        Duration duration = Duration.between(start, end);
        long mill = duration.toMillis();
        System.out.println("mill: " + mill);
    }
}
