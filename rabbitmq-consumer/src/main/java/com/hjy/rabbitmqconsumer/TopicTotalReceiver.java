package com.hjy.rabbitmqconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.woman")
@Slf4j
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(String message) {
        log.info("消费者收到消息:{}", message);
    }

}


