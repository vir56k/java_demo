package com.zhangyf.javademo;

/**
 * 示例：接口里可写静态方法
 */
interface InterfaceDemo6 {
    static void createEntity() {
        System.out.println("createEntity....");
    }
}

// 调用者
class MainClass6 {
    public static void main(String[] args) {
        InterfaceDemo6.createEntity();
    }
}
