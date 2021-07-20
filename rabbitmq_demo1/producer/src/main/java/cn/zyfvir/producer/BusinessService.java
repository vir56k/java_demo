package cn.zyfvir.producer;

import cn.zyfvir.producer.config.RabbitConfig;
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


    public void sendMessage(String msg) {
        System.out.println("# sendMessage,msg=" + msg);


        String routingKey = RabbitConfig.ROUTING_KEY;
        String exchangeName = RabbitConfig.EXCHANGE_NAME;
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
    }

}
