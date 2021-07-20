package cn.zyfvir.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 */
@Slf4j
@Configuration
public class RabbitCallbackConfig {

    // 用来确认生产者 producer 将消息发送到 broker 的回调
    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback() {
        return new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                String id = correlationData == null ? "" : correlationData.getId();
                if (ack) {
                    // log.info(String.format("# [%s] 投递到 broker 成功!, cause=%s", id, cause));
                } else {
                    log.error(String.format("# [%s] 投递到 broker 失败!, cause=%s", id, cause));
                }
            }
        };
    }

    // 发布到交换机，但没有匹配的目标队列 时，退货
    @Bean
    public RabbitTemplate.ReturnsCallback returnsCallback() {
        return new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                int replyCode = returnedMessage.getReplyCode();
                String replyText = returnedMessage.getReplyText();
                String exchange = returnedMessage.getExchange();
                System.out.println(String.format("# 退货消息：原因=%s, replyCode=%s, exchange=%s", replyText, replyCode, exchange));
            }
        };
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(confirmCallback());
        rabbitTemplate.setReturnsCallback(returnsCallback());
        // 强制标志，当 setReturnsCallback 被设置时，这里要设置为 true
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }
}
