package com.zyc.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    private static Logger log = LoggerFactory.getLogger(SendMail.class);

    public static final String SENDER = "77742344@qq.com";

    public static boolean send(String addressee, String subject, String text) {
        try {
            //用于读取配置文件
            Properties props = new Properties();

            //开启Debug调试
            props.setProperty("mail.debug", "true");

            //发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");

            //发送邮件服务器的主机名
            props.setProperty("mail.host", "smtp.qq.com");

            //发送邮件协议
            props.setProperty("mail.transport.protocol", "smtp");

            //开启ssl加密（并不是所有的邮箱服务器都需要，但是qq邮箱服务器是必须的）
            MailSSLSocketFactory msf = new MailSSLSocketFactory();
            msf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", msf);

            //获取Session会话实例（javamail Session与HttpSession的区别是Javamail的Session只是配置信息的集合）
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    //用户名密码验证（取得的授权吗）
                    return new PasswordAuthentication(SENDER, "gozndgdhwvjxcaga");
                }

            });

            //抽象类MimeMessage为实现类  消息载体封装了邮件的所有消息
            Message message = new MimeMessage(session);

            //设置邮件主题
            message.setSubject(subject);

            //封装需要发送电子邮件的信息
            message.setText(text);

            //设置发件人地址
            message.setFrom(new InternetAddress(SENDER));

            //此类的功能是发送邮件 又会话获得实例
            Transport transport = session.getTransport();

            //开启连接
            transport.connect();

            //设置收件人地址邮件信息
            transport.sendMessage(message, new Address[]{new InternetAddress(addressee)});

            //邮件发送后关闭信息
            transport.close();
        } catch (Exception e) {
            log.info("邮件发送异常");
            return false;
        }
        return true;
    }

}
