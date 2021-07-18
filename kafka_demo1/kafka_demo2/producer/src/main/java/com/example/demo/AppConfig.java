package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class AppConfig {
    @Autowired
    KafkaDemo kafkaDemo;

    @Bean
    public ApplicationListener onStarted() {
        return new ApplicationListener<ApplicationStartedEvent>() {
            @Override
            public void onApplicationEvent(ApplicationStartedEvent applicationEvent) {
                System.out.println("# ApplicationStartedEvent");
                kafkaDemo.start();
            }
        };
    }
}
