package cn.zyfvir.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class RabbitMessageListener {
    public static final String QUEUE_NAME = "first_queue";

    @RabbitListener(queues = QUEUE_NAME)
    public void receive(String msg) {
        System.out.println(msg);
    }
}
