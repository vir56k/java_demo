package cn.zyfvir.demo.controller;

import cn.zyfvir.demo.common.MyException;
import cn.zyfvir.demo.common.MyException2;
import cn.zyfvir.demo.common.MyException3;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/7/6 10:01
 */
@Controller
public class HelloWorldController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("yourname", "zhangsan");
        return "welcome";
    }

    @RequestMapping(value = "/exception1", method = RequestMethod.GET)
    public String expception1(Model model) {
        throw new MyException();
    }

    @RequestMapping(value = "/exception2", method = RequestMethod.GET)
    public String exception2(Model model) {
        throw new MyException2();
    }

    @RequestMapping(value = "/exception3", method = RequestMethod.GET)
    public String exception3(Model model) {
        throw new MyException3();
    }


    // @ExceptionHandler 注解，在这里会捕获这个类异常
    @ExceptionHandler(MyException2.class)
    public String handleException2() {
        return "errorrrr";
    }
}
