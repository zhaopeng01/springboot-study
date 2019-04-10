package com.zyc.active;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhaopeng
 * @description: 点对点发送消息
 * @date 2019/4/9
 */
@Component
public class O2OSend {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String message) {
        System.out.println("发送queue消息 :" + message);
        jmsTemplate.convertAndSend("zyc", message);
    }
}