package com.zhangyf.javademo;

/**
 * 示例：构造方法的引用
 */
class User {
    String name;
    int age;

    // 构造方法1：无参
    public User() {
    }

    // 构造方法2：两个参数
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "_" + age;
    }
}

// 一个工厂。注意是个 接口, 不是实现类
interface UserFactory {
    User create(String name, int age);
}

// 调用者
class MainClass5 {
    public static void main(String[] args) {
        // 通过方法引用了 User类的构造方法，好像 "粘" 在了一期。
        UserFactory userFactory = User::new;
        // 编译器通过推断，识别到 "这是两个参数的构造方法"
        User user = userFactory.create("zyf", 30);
        System.out.println(user);
    }
}
