package cn.zyfvir.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 *
 */
@Component
@Slf4j
public class RabbitMessageListener {
    public static final String QUEUE_NAME = "first_queue";

//    // 异步 接收消息
//    @RabbitListener(queues = QUEUE_NAME)
//    public void receive(String msg) {
//        System.out.println(msg);
//    }

    int i = 0;

    // 异步 接收消息
    @RabbitListener(queues = QUEUE_NAME, ackMode = "MANUAL")
    public void receiveForManual(String msg, Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println(msg);

        String returned_message_correlation = message.getMessageProperties().getHeader("spring_returned_message_correlation");
        log.info(String.format("# 触发 receiveForManual msg =%s ,correlationId = %s", msg, returned_message_correlation));

        i++;
        if (i % 3 == 0) {
            log.info("# 接收消息...");
            channel.basicAck(tag, false);
        } else if (i % 3 == 1) {
            log.error("# 消息已重复处理失败,拒绝再次接收...");
            channel.basicReject(tag, false);
        } else if (i % 3 == 2) {
            log.error("# 消息即将再次返回队列处理...");
            channel.basicNack(tag, false, true);
        }

    }
}
