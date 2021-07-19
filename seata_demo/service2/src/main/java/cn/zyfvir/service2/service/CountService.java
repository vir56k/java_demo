package cn.zyfvir.service2.service;

import cn.zyfvir.service2.persistence.Count;
import cn.zyfvir.service2.persistence.CountMapper;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountService.class);

    @Autowired
    private CountMapper countMapper;

    /**
     * 减库存，下订单
     *
     * @param id
     * @param count
     */
    public void changeCount(int id, int count) {
        LOGGER.info("# changeCount... xid: " + RootContext.getXID());

        Count money1 = countMapper.selectById(id);
        LOGGER.info("# 仓库数量是: " + money1.getCount() + ", 期望累加 " + count);
        if (money1.getCount() + count < 0) {
            throw new RuntimeException("没有足够的  存量");
        }
        money1.setCount(money1.getCount() + count);
        countMapper.updateById(money1);
    }
}
