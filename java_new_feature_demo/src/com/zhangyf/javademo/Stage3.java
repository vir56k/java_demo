package com.zhangyf.javademo;

/**
 *
 */

// 定义一个 " 函数式接口 "，它必须只有一个方法。
@FunctionalInterface
interface MyConverter<FROM, TO> {
    TO convert(FROM from);
}

class MainClass3 {

    public static void main(String[] args) {
        //使用 lambda 实现了一个 "函数式接口"： 将 字符串 转换成 整数
        MyConverter<String, Integer> myConvert = (from) -> Integer.valueOf(from);
        //使用 "函数式接口"
        int intPara = myConvert.convert("1234");

        System.out.println(intPara);
    }

}
