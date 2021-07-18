package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
@Service
public class KafkaDemo {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void start() {
        System.out.println("# start ");
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static final String TOPIC_NAME = "topic2";

    private void justSend() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String data = " 一个消息-" + now;
        System.out.printf("# send ready: %s \n", data);
        ListenableFuture<SendResult<String, String>> callback = kafkaTemplate.send(TOPIC_NAME, data);
        callback.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.printf("# send onFailure: %s \n", data);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                System.out.printf("# send onSuccess: %s \n", data);
            }
        });

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            final int MAX = 100;
            int i = 0;
            while (i++ < MAX) {
                justSend();
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


}
