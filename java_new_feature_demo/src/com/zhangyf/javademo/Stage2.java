package com.zhangyf.javademo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 示例：Lambda 表达式
 */
public class Stage2 {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("banana", "apple", "mike", "xenia");
        // 使用 Lambda 时：很简洁。
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println("排序后：" + names);

        // 对比于：当不用 Lambda ，传统写法，很长。
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

}
