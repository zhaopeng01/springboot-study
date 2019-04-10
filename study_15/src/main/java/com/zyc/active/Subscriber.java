package com.zyc.active;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @description: 订阅话题
 *
 * @author zhaopeng
 * @date 2019/4/9
 */
@Service
public class Subscriber {

    @JmsListener(destination = "zycTopic", containerFactory = "myJmsContainerFactory")
    public void subscribe(String text) {
        System.out.println("-----> 收到订阅msg为:" + text);
    }
}