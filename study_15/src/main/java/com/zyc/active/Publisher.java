package com.zyc.active;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author zhaopeng
 * @description: 发布话题
 * @date 2019/4/9
 */
@Service
public class Publisher {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void publish(String destinationName, String message) {
        Destination destination = new ActiveMQTopic(destinationName);
        System.out.println("发送topic消息 :" + message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
