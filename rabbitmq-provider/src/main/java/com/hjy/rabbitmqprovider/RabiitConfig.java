package com.hjy.rabbitmqprovider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hjy
 * @date 2020/5/1 16:36
 */
@Configuration
@Slf4j
public class RabiitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory，才能出发回调函数，无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        /**
         * 调用情况一： 消息推送到server，但是在server里找不到交换机
         * 调用情况二： 消息推送到server，找到交换机了，但是没找到队列
         * 调用情况三： 消息推送到sever，交换机和队列啥都没找
         * 调用情况四： 消息推送成功
         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("ConfirmCallback:     "+"相关数据："+correlationData);
                log.info("ConfirmCallback:     "+"确认情况："+ack);
                log.info("ConfirmCallback:     "+"原因："+cause);
            }
        });
        /**
         * 调用情况一： 消息推送到server，找到交换机了，但是没找到队列
         */
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message
                    , int replyCode, String replyText, String exchange, String routingKey) {
                log.info("ReturnCallback:     "+"消息："+ message);
                log.info("ReturnCallback:     "+"回应码："+ replyCode);
                log.info("ReturnCallback:     "+"回应信息："+ replyText);
                log.info("ReturnCallback:     "+"交换机："+ exchange);
                log.info("ReturnCallback:     "+"路由键："+ routingKey);
            }
        });
        return rabbitTemplate;
    }


}
