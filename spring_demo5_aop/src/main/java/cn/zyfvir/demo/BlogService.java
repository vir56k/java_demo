package cn.zyfvir.demo;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// 博客服务
interface BlogService {
    public void postBlog(String blogContet);
}

@Component
class BlogServiceImpl implements BlogService {

    public void postBlog(String blogContet) {
        System.out.println("发布了一遍博客");
    }

}


// 日志记录员
// 把自己也注册成 Spring 组件
@Aspect
@Component
class LogAspect {

    // 切点表达式
    @Pointcut("execution(* cn.zyfvir.demo.BlogService.postBlog(..))")
    public void doPostPoint() {
    }

    @Before("doPostPoint()")
    public void before() {
        System.out.println("## before...");
    }

    @After("doPostPoint()")
    public void after() {
        System.out.println("## after...");
    }

    @AfterReturning("doPostPoint()")
    public void afterReturning() {
        System.out.println("## AfterReturning...");
    }

    @AfterThrowing("doPostPoint()")
    public void afterThrowing() {
        System.out.println("## AfterThrowing...");
    }
}