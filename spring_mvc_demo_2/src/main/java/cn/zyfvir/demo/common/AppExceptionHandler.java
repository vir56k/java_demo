package cn.zyfvir.demo.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 */
@ControllerAdvice
public class AppExceptionHandler {

    // @ExceptionHandler 注解，在这里会捕获这个类异常
    @ExceptionHandler(MyException3.class)
    public String handleException() {
        return "errorrrr";
    }

}
