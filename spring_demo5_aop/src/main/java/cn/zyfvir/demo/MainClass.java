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
        BlogService bean = context.getBean(BlogService.class);
        bean.postBlog("《从入门到精通》");
        System.out.println("执行结束，" + bean);
    }

}
