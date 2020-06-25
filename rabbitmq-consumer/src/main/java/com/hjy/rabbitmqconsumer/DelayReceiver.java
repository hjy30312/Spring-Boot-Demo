package com.hjy.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hjy
 * @date 2020/6/25 20:42
 */
@Component
public class DelayReceiver {


    @RabbitListener(queues = "delay_queue_1")
    public void receive(String msg) {
        System.out.println("消息接收时间:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("接收到的消息:"+msg);
    }

}
