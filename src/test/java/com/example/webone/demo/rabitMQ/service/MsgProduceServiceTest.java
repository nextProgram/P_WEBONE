package com.example.webone.demo.rabitMQ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
     */
    @Test
    public void sendOrder() {
        msgProduceService.sendOrder("myOrder","fruit","fruit message");
    }
}