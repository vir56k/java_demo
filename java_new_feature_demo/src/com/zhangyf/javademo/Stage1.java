package com.zhangyf.javademo.stage1;

/**
 * 示例：接口中写"默认方法"
 */
interface IDemo {
    void hello(); // 这个方法需要 "被实现"。

    default void hi() {
        System.out.println("hi，zyf");
    }
}

class Demo1 implements IDemo {

    // 实现类仅仅 "实现 hello 方法就可。
    // 上面的 hi 方法无需实现"
    @Override
    public void hello() {
        System.out.println("hello，zyf");
    }
}

// 执行示例
class Stage1 {

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.hello();
        demo1.hi();
    }

}