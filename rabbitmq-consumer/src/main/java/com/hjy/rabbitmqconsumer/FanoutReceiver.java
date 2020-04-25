package com.hjy.rabbitmqconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hjy
 * @date 2020/4/25 22:40
 */
@Component
@Slf4j
public class FanoutReceiver {

    @RabbitListener(queues = "fanout.A")
    public void processFanoutA(String message) {
        log.info("消费者收到消息:{}", message);
    }

    @RabbitListener(queues = "fanout.B")
    public void processFanoutB(String message) {
        log.info("消费者收到消息:{}", message);
    }

    @RabbitListener(queues = "fanout.C")
    public void processFanoutC(String message) {
        log.info("消费者收到消息:{}", message);
    }
}
