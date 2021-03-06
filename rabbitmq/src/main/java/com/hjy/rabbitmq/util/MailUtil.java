package com.hjy.rabbitmq.util;

import com.hjy.rabbitmq.pojo.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author hjy
 * @date 2020/4/25 16:26
 */
@Component
@Slf4j
public class MailUtil {


    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 简单发送邮件
     *
     * @param mail
     * @return
     */
    public boolean send(Mail mail) {
        String to = mail.getTo();// 目标邮箱
        String title = mail.getTitle();// 邮件标题
        String content = mail.getContent();// 邮件正文

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("邮件发送失败, to: {}, title: {}", to, title, e);
            return false;
        }
        return true;
    }


}
