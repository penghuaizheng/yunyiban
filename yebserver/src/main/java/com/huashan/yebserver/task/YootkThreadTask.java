package com.huashan.yebserver.task;

import com.huashan.yebserver.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class YootkThreadTask {
    @Autowired
    private JavaMailSender javaMailSender;

    @Async // 异步处理
    public void startTaskHandle(Employee employee,String msg) { // 这是一个普通的方法
        log.info("【邮件发送】开启，执行线程：{}", Thread.currentThread().getName());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("3100366252@qq.com"); // 发送者
        message.setTo(employee.getEmail()); // 接收者
        message.setSubject("云易办"); // 主题
        message.setText(msg); // 内容
        javaMailSender.send(message); // 发送
        log.info("【邮件发送】结束，执行线程：{}", Thread.currentThread().getName());
    }
}