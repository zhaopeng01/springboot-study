package com.zyc.study_14.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @description: 消费者
 *
 * @author zhaopeng
 * @date 2019/4/8
 */

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic1")
    public void listen(ConsumerRecord<?,String> record) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
    }
}