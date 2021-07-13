package com.zhangyf.javademo;

/**
 * 示例：方法引用
 */
@FunctionalInterface
interface MyConverter1<FROM, TO> {
    TO convert(FROM from);

}

class MainClass4 {
    public static void main(String[] args) {
        MyConverter1<String, Integer> myConvert = Integer::valueOf;
        int intPara = myConvert.convert("1234");
        System.out.println(intPara);
    }
}