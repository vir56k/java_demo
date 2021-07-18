package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class MyKafkaConsumer {
    private static final String TOPIC_NAME = "topic2";

    @KafkaListener(topics = TOPIC_NAME)
    public void processMessage(ConsumerRecord<String, String> record) {
        System.out.println(String.format("# record: %s", record));
        System.out.println(String.format("\t\t# 收到消息: %s", record.value()));
    }

}
