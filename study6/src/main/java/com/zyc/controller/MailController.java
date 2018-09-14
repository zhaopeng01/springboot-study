package com.zyc.controller;

import com.zyc.utils.SendMail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhaopeng
 * @Description:发送邮件
 * @email zp152527@163.com
 */

@RestController
@RequestMapping("/mail")
public class MailController {


    @GetMapping("/send")
    public void send() {
        SendMail.send("zp152527@163.com", "这是主题", "这是内容");
    }
}
