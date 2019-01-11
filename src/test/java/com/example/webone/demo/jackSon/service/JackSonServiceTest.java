package com.example.webone.demo.jackSon.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author lhx
 * @date 2019/1/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JackSonServiceTest {

    @Autowired
    private JackSonService jackSonService;

    @Test
    public void readTree() {
        String json = "{\"name\":\"lhx\",\"major\":\"computer\",\"age\":27}";
        String jsonKey = "name";
        try {
            String jsonValue = jackSonService.readTree(json,jsonKey);
            System.out.println("key["+jsonKey+"],value["+jsonValue+"]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}