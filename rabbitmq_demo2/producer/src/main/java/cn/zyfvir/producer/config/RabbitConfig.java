package cn.zyfvir.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class RabbitConfig {
    // 路由的 key
    public static final String ROUTING_KEY = "hello_routing_key";
    public static final String EXCHANGE_NAME = "zyf_direct_exchange";
    public static final String QUEUE_NAME = "first_queue";

    //  一个队列
    @Bean
    public Queue getFirstQueue() {
        return new Queue(QUEUE_NAME);
    }

    // 一个 直接交换机
    @Bean
    public DirectExchange getDirectExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // 进行绑定
    @Bean
    public Binding getBinding() {
        return BindingBuilder.bind(getFirstQueue()).to(getDirectExchange()).with(ROUTING_KEY);
    }
}
