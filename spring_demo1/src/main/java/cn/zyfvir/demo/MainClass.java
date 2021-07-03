package cn.zyfvir.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/7/3 22:13
 */
public class MainClass {
    public static void main(String[] args) {
//        demoNoSpringExcute();

        //demoXmlSpring();
        demoJavaSpring();
    }

    // 使用java 方式
    private static void demoJavaSpring() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 启用组件扫描，扫描查找任何带 @Component ，@Configuration 注解的类
        context.scan("cn.zyfvir.demo");
        context.refresh();
        Hero bean = context.getBean(Hero.class);
        bean.play();
    }

    // 使用 xml 方式 配置 spring
    private static void demoXmlSpring() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Hero bean = context.getBean(Hero.class);
        bean.play();
    }

    //不使用 spring 的代码
    private static void demoNoSpringExcute() {
        // 创建 对象
        Hero hero = new Hero(new SwordAction(System.out));
        // 执行动作
        hero.play();
    }
}
