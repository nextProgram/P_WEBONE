package com.example.webone.demo.rabitMQ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * 测试消息的发布
 * @author lhx
 * @date 2019/6/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MsgProduceServiceTest {

    @Autowired
    private MsgProduceService msgProduceService;

    /**
     * 测试一个exchange绑定一个queue
     */
    @Test
    public void sendMsg() {
        msgProduceService.sendMsg();
    }

    /**
     * 一个exchange绑定两个queue
     * 发送5条消息 结果：发送成功 报错：Exception delivering confirm
     */
    @Test
    public void sendOrder() {
        for(int i = 0;i<5;i++){
            CorrelationData correlationData = new CorrelationData(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
            msgProduceService.sendOrder("myOrder","fruit","fruit message "+i,correlationData);
        }
    }
}