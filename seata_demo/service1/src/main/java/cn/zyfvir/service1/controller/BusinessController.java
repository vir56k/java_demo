package cn.zyfvir.service1.controller;

import cn.zyfvir.service1.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BusinessController {

    @Autowired
    private MoneyService moneyService;

    /**
     * 购买下单，模拟全局事务提交
     *
     * @return
     */
    @RequestMapping("/pay/commit")
    public Boolean payCommit(HttpServletRequest request) {
        moneyService.payMoney(1, -1);
        return true;
    }

    /**
     * 购买下单，模拟全局事务回滚
     *
     * @return
     */
    @RequestMapping("/pay/rollback")
    public Boolean purchaseRollback() {
        try {
            // 支付1000 会失败
            moneyService.payMoney(1, -90);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
