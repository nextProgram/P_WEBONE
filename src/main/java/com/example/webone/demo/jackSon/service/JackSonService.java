package com.example.webone.demo.jackSon.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lhx
 * @date 2019/1/11
 */
@Service
public class JackSonService {
    private final static Logger logger = LoggerFactory.getLogger(JackSonService.class);

    //jackSon利用ObjectMapper类转化POJO与JSON
    @Autowired ObjectMapper objectMapper;

    /**
     * jackSon树遍历-获取json指定key的value
     * @param jsonStr json
     * @param jsonKey key
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public String readTree(String jsonStr,String jsonKey) throws JsonProcessingException,IOException {
        logger.info("json:"+jsonStr);
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        String jsonValue = jsonNode.get(jsonKey).asText();//若为int类型，则用asInt();
        logger.info("key["+jsonKey+"],value["+jsonValue+"]");
        return jsonValue;
    }

}
