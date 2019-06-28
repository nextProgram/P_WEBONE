package com.example.webone.demo.rabitMQ;

import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * rabbitMq 配置类
 * @author lhx
 * @date 2019/6/27
 */
@Configuration
public class RabbitConfig {
    private final static Logger logger = LoggerFactory.getLogger(RabbitConfig.class);


    /*@Autowired
    private RabbitTemplate rabbitTemplate;*/

    /**
     * 定制amqp模版
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调 ，即消息发送到exchange再ack，即保证消息发送到了broker
     * ReturnCallback接口用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调，即消息发送不到任何一个队列中则ack
     */
    /*@Bean
    public RabbitTemplate rabbitTemplate() {
        // 消息发送失败返回到队列中, 需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        // 消息确认, 需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                //发送成功
                logger.debug("消息发送到exchange成功,id: {}", correlationData.getId());
            } else {
                //发送失败
                logger.debug("消息发送到exchange失败,原因: {}", cause);
            }
        });
        // 消息返回, 需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            logger.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
            });
        return rabbitTemplate;
    }*/
}
