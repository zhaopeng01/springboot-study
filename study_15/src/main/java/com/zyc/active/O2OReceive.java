package com.zyc.active;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @description: 点对点接收消息
 *
 * @author zhaopeng
 * @date 2019/4/9
 */
@Component
public class O2OReceive {
    @JmsListener(destination = "zyc")
    public void receive(String message) {
        System.out.println("收到的 msg 是：" + message);
    }
}
