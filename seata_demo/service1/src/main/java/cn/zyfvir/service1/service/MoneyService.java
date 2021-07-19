package cn.zyfvir.service1.service;

import cn.zyfvir.service1.client.Service2Client;
import cn.zyfvir.service1.persistence.Money;
import cn.zyfvir.service1.persistence.MoneyMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyService.class);

    @Autowired
    private Service2Client storageClient;

    @Autowired
    private MoneyMapper moneyMapper;

    /**
     * 减库存，下订单
     *
     * @param id
     * @param money
     */
    @GlobalTransactional
    public void payMoney(int id, int money) {
        LOGGER.info("# 分布式事务 开始... xid: " + RootContext.getXID());

        Money money1 = moneyMapper.selectById(id);
        if (money1.getMoney() + money < 0) {
            throw new RuntimeException("没有足够的钱");
        }
        money1.setMoney(money1.getMoney() + money);
        moneyMapper.updateById(money1);

        storageClient.changeMoney(id, money * 100);
    }
}
