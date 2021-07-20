package cn.zyfvir.producer.config;

import lombok.extern.slf4j.Slf4j;
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
            public void confirm(CorrelationData correlationData, boolean ack, String str) {
                if (ack) {
                    log.info("# 投递到 broker 成功!, str=" + str);
                } else {
                    log.info("# 投递到 broker 失败!");
                }
            }
        };
    }
}
