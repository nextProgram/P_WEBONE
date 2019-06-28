package com.example.webone.demo.rabitMQ.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 发送消息到rabbitMQ
 * 先启动接收端，再启动发送端发送
 * 不启动接收端，也可以正常发送，消息会暂存再rabbitMQ，接收端启动自动从中获取
 * @author lhx
 * @date 2019/6/20
 */
@Service
public class MsgProduceService {
    private final static Logger logger = LoggerFactory.getLogger(MsgProduceService.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * 一个exchange绑定一个queue
     */
    public void sendMsg(){
        rabbitTemplate.convertAndSend("myQueue", "now ");
        logger.info("----->发送消息成功");
    }

    /**
     * 发送消息
     * 一个exchange绑定两个queue
     * 通过routingKey找到对应queue
     * 定制amqp模版
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调 ，即消息发送到exchange再ack，即保证消息发送到了broker
     * ReturnCallback接口用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调，即消息发送不到任何一个队列中则ack
     * @param exchange 交换器名称
     * @param routingKey routingKey
     * @param message 消息
     */
    public void sendOrder(String exchange,String routingKey,String message){
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
        /*rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            logger.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });*/
        //发送消息
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
        logger.info("----->发送消息成功,exchange:{},routingKey:{},message:{}",exchange,routingKey,message);
    }
}
