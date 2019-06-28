package com.example.webone.demo.redis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 测试jedis
 * @author lhx
 * @date 2018/12/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JedisSevviceTest {
    @Autowired
    private JedisSevvice jedisSevvice;

    @Test
    public void jedisTest() {
        jedisSevvice.jedisTest();
    }
}