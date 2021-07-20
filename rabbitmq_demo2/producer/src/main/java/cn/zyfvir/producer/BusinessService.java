package cn.zyfvir.producer;

import cn.zyfvir.producer.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
@Service
public class BusinessService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    public void sendMessage(String msg) {
        System.out.println("# sendMessage,msg=" + msg);

        String routingKey = RabbitConfig.ROUTING_KEY;
        String exchangeName = RabbitConfig.EXCHANGE_NAME;
        int millis = atomicInteger.getAndIncrement();
        CorrelationData correlationData = new CorrelationData(millis + "");
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg, correlationData);
        // 下面这个方法 故意触发退货，因为没有 指定的路由可以使用。
        // rabbitTemplate.convertAndSend("333", msg);
    }

}
