package com.example.webone.demo.redis.service;

import com.example.webone.demo.base.ReturnVO;
import com.example.webone.demo.redis.RedisUseInterface;
import com.example.webone.demo.redis.vo.JedisRequestVO;
import com.example.webone.demo.redis.vo.JedisReturnVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lhx
 * @date 2018/12/12
 */
@Service
public class JedisSevvice {
    private final static Logger logger = LoggerFactory.getLogger(JedisSevvice.class);
    @Resource
    private RedisUseInterface redisUseInterface;

    public void jedisTest(){
        ReturnVO<JedisReturnVO> returnVO = new ReturnVO<>();
        JedisRequestVO jedisRequestVO = new JedisRequestVO();
        jedisRequestVO.setKey("lhx");
        jedisRequestVO.setValue("1725");
        returnVO = redisUseInterface.jedisForStringSet(jedisRequestVO);
        logger.info("returnVO:retCode["+returnVO.getRetCode()+"],retMsg["+returnVO.getRetMsg()+"]");
    }
}
