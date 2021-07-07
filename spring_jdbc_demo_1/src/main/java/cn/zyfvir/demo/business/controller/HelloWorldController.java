package cn.zyfvir.demo.business.controller;

import cn.zyfvir.demo.business.dao.HelloRepository;
import cn.zyfvir.demo.business.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    HelloRepository helloRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        int count = helloRepository.getCount();
        String lastName = helloRepository.getLastName();
        Actor actor = helloRepository.getActor();

        model.addAttribute("lastName", lastName);
        model.addAttribute("count", count);
        model.addAttribute("actor", "" + actor);

        System.out.println(String.format("lastName=%s", lastName));
        System.out.println(String.format("count=%s", count));
        System.out.println(String.format("actor=%s", actor));
        return "welcome";
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage(Model model) {
        model.addAttribute("yourname", "zhangsan");
        return "manage";
    }
}
