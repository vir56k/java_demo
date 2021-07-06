package cn.zyfvir.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/7/6 10:01
 */
@Controller
@RequestMapping("/main")
public class HelloWorldController {


    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String sayHello(Model model) {
        model.addAttribute("yourname", "zhangsan");
        return "welcome";
    }

}
