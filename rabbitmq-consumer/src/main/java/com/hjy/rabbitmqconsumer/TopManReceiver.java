package com.hjy.rabbitmqconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hjy
 * @date 2020/4/25 22:33
 */
@Component
@RabbitListener(queues = "topic.man")
@Slf4j
public class TopManReceiver {

    @RabbitHandler
    public void process(String message) {
        log.info("消费者收到消息:{}", message);
    }
}

