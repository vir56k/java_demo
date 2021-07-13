package com.zhangyf.javademo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 示例： Stream
 */
public class Stage8 {
    public static void main(String[] args) {
        // 准备演示数据
        List<String> lst = new ArrayList<>();
        lst.add("s1");
        lst.add("s99");
        lst.add("s3");
        lst.add("ccc");
        lst.add("zzz");
        lst.add("111");

        System.out.println("-------- 示例：filter ---------");
        // filter 过滤器：接收一个表达式判断，返回boolean值，决定是否过滤掉
        lst.stream().filter((it) -> it.startsWith("s")).forEach(System.out::println);

        System.out.println("-------- 示例：sorted ---------");
        // sorted：排序
        lst.stream().sorted().forEach(System.out::println);

        System.out.println("-------- 示例：map ---------");
        // map：对子元素 map 映射后，成为新的集合
        lst.stream().map(String::toUpperCase).forEach(System.out::println);

        System.out.println("-------- 示例：匹配 ---------");
        // anyMatch: 只要有一个 匹配成功
        boolean result1 = lst.stream().anyMatch((s) -> s.startsWith("c"));
        System.out.println(result1);// true
        // allMatch： 都匹配成功，则为 true
        boolean result2 = lst.stream().allMatch((s) -> s.startsWith("c"));
        System.out.println(result2);// fasle
        // noneMatch : 都不匹配，则为 true
        boolean result3 = lst.stream().noneMatch((s) -> s.startsWith("$"));
        System.out.println(result3);//

        System.out.println("-------- 示例：count ---------");
        // count: 计算总数。 先过滤 s 开头的，再计算个总数
        long count1 = lst.stream().filter((p) -> p.startsWith("s")).count();
        System.out.println("count1=" + count1);//

        System.out.println("-------- 示例：reduce ---------");
        // reduce: 归纳。 传入两个参数，返回一个结果
        Optional<String> optional = lst.stream().sorted().reduce((t1, t2) -> t1 + "," + t2);
        System.out.println("optional=" + optional);//
    }
}
