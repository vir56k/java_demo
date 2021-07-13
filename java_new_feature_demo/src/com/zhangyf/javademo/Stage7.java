package com.zhangyf.javademo;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 示例：JDK8内置的 函数式接口
 */

// 调用者
class MainClass7 {
    public static void main(String[] args) {
        // 谓词 Predicate
        Predicate<String> n1 = (s) -> s.length() > 0;
        boolean foo = n1.test("foo"); // true
        Predicate n2 = Objects::isNull;
        Predicate<String> n3 = String::isEmpty;

        // 功能 Function, 它可引用 "一个参数"和带返回值的方法。
        Function<String, Integer> f1 = Integer::valueOf;
        Integer interge1 = f1.apply("33432");

        // 提供者 Supplier ，它可以引用一个 构造方法
        Supplier supplier1 = Object::new;
        supplier1.get();

        // Consumers, 它可以引用 "一个参数"，无返回值的方法
        Consumer<String> consumer1 = (str) -> System.out.println(str);
        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("hello");

        // comparator1 比较器
        Comparator<String> comparator1 = (p1, p2) -> p1.compareTo(p2);
        comparator1.compare("P1","p3");

        // Optional, 它不是一个 函数式接口。它 用于辅助判断 空值
        Optional<String> optional1 = Optional.of("some");
        optional1.isPresent(); // 有值，则 true
        optional1.get();
    }
}
