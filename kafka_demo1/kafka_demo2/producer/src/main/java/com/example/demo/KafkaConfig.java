package com.example.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class KafkaConfig {
    private static final String TOPIC_NAME = "topic2";

    // 创建一个主题 topic
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(TOPIC_NAME)
                .partitions(2)
                .replicas(1)
                .compact()
                .build();
    }

}
