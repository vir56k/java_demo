package cn.zyfvir.demo.business.controller;

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
public class HelloWorldController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("yourname", "zhangsan");
        return "welcome";
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage(Model model) {
        model.addAttribute("yourname", "zhangsan");
        return "manage";
    }
}
