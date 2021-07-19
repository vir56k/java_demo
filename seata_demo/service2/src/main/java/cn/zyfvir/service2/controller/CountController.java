package cn.zyfvir.service2.controller;

import cn.zyfvir.service2.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountController {

    @Autowired
    private CountService moneyService;

    /**
     * 购买下单，模拟全局事务提交
     *
     * @return
     */
    @RequestMapping("/count/change")
    public Boolean payCommit(@RequestParam Integer id, @RequestParam Integer count) {
        moneyService.changeCount(id, count);
        return true;
    }
}
