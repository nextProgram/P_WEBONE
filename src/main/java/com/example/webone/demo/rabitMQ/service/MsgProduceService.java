package com.example.webone.demo.rabitMQ.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Negative Acknowledgment In exceptional cases when the broker is unable to handle messages successfully, instead of a basic.ack, the broker will send a basic.nack. In this context, fields of the basic.nack have the same meaning as the corresponding ones in basic.ack and the requeue field should be ignored. By nack'ing one or more messages, the broker indicates that it was unable to process the messages and refuses responsibility for them; at that point, the client may choose to re-publish the messages.
     * After a channel is put into confirm mode, all subsequently published messages will be confirmed or nack'd once. No guarantees are made as to how soon a message is confirmed. No message will be both confirmed and nack'd.
     * basic.nack will only be delivered if an internal error occurs in the Erlang process responsible for a queue.
     * In addition to this, Spring AMQP generates a Nack if the connection is closed before an ack is received (again - very rare).
     * If you want to get notified about the inability to deliver a message to a queue, you must enable publisher returns and set mandatory to true, and the message will be returned to you.
     * @param exchange 交换器名称
     * @param routingKey routingKey
     * @param message 消息
     */
    public void sendOrder(String exchange,String routingKey,String message,CorrelationData correlationData1){
        //消息确认, 需要配置 publisher-confirms: true(类似于connectionFactory.setPublisherConfirms(true);)
        rabbitTemplate.setMandatory(true);
        //采用实现接口的方式设置ConfirmCallback
        //rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                //发送成功，做相应处理
                logger.debug("消息发送到exchange成功,id: {}", correlationData.getId());
            } else {
                //发送失败
                logger.debug("消息发送到exchange失败,原因: {}", cause);
            }
        });
        //消息发送失败则返回, 需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback((message1, replyCode, replyText, exchange1, routingKey1) -> {
            String correlationId = message1.getMessageProperties().getCorrelationId();
            logger.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange1, routingKey1);
        });

        //发送消息
        logger.debug("publisher CallBackConfirm ID: {}",correlationData1.getId());
        rabbitTemplate.convertAndSend(exchange,routingKey,message,correlationData1);
        logger.info("----->发送消息成功,exchange:{},routingKey:{},message:{}",exchange,routingKey,message);
    }

    /**
     * 发送方确认
     *  需要实现接口：implements RabbitTemplate.ConfirmCallback
     *  需要发送之前设置：rabbitTemplate.setConfirmCallback(this)
     *  该方法等同于上面setConfirmCallback
     * @param correlationData
     * @param ack
     * @param cause
     */
  /*  @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.debug("CallBackConfirm ID: {}",correlationData.getId());
        if(ack) {
            logger.debug("CallBackConfirm 消息发送成功！");
        }else {
            logger.debug("CallBackConfirm 消息发送失败！");
        }
        if(cause!=null) {
            logger.debug("CallBackConfirm Cause: " + cause);
        }
    }*/
}
