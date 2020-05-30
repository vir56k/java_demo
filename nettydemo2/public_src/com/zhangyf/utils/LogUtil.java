package com.zhangyf.utils;

public class LogUtil {

    public static void d(String tag, String str) {
        System.out.println(String.format("[%s] %s", tag, str));
    }

    public static void d(String tag, String format, Object... para) {
        System.out.println(String.format("[%s] %s", tag, String.format(format, para)));
    }
}
