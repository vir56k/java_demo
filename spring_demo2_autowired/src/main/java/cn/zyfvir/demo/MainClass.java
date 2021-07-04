package cn.zyfvir.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/7/3 22:13
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PlayerConfig.class);
        Player bean = context.getBean(Player.class);
        bean.startPlay();
    }

}
