package com.zhangyf.javademo;



import java.util.concurrent.atomic.AtomicInteger;

class Demo1 {
    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public void setValue(int oldVal, int newVal) {
        atomicInteger.compareAndSet(oldVal, newVal);
    }

    public int getValue() {
        return atomicInteger.get();
    }
}

class Main {

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.setValue(100, 222);  // 失败，预期是 100 而实际却是 1
        System.out.println("#1 修改后" + demo1.getValue());

        demo1.setValue(1, 2222);  // 成功
        System.out.println("#2 修改后" + demo1.getValue());
    }
}
