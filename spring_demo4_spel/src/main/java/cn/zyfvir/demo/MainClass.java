package cn.zyfvir.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/7/3 22:13
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MySrpingConfig.class);
        MyPropertyConfig bean = context.getBean(MyPropertyConfig.class);

        System.out.println("读取结果："+bean);
    }

}
