package com.zyc;

import com.zyc.active.O2OSend;
import com.zyc.active.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class Study15Application {

    @Autowired
    O2OSend send;
    @Autowired
    Publisher publisher;

    @RequestMapping("/sendQueue")
    public void sendQueue() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10; i++) {
            send.send("------>" + i);
        }
        stopWatch.stop();
        System.out.println("发送queue消息耗时: " + stopWatch.getTotalTimeMillis());
    }

    @RequestMapping("/sendTopic")
    public void sendTopic() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10; i++) {
            publisher.publish("zycTopic", "------>" + i);
        }
        stopWatch.stop();
        System.out.println("发送topic消息耗时: " + stopWatch.getTotalTimeMillis());
    }

    public static void main(String[] args) {
        SpringApplication.run(Study15Application.class, args);
    }

}
