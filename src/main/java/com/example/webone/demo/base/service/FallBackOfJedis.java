package com.example.webone.demo.base.service;

import com.example.webone.demo.base.ReturnVO;
import com.example.webone.demo.jackSon.service.JackSonService;
import com.example.webone.demo.redis.RedisUseInterface;
import com.example.webone.demo.redis.vo.JedisRequestVO;
import com.example.webone.demo.redis.vo.JedisReturnVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lhx
 * @date 2019/2/18
 */
@Component
public class FallBackOfJedis implements RedisUseInterface {

    private final static Logger logger = LoggerFactory.getLogger(JackSonService.class);
    @Override
    public ReturnVO<JedisReturnVO> jedisForStringSet(JedisRequestVO jedisRequestVO){
        logger.info("调用[jedisForStringSet]失败,请联系管理员,进入fallback");
        ReturnVO<JedisReturnVO> jedisReturnVO = new ReturnVO<>();
        jedisReturnVO.setRetCode("0");
        jedisReturnVO.setRetMsg("调用[jedisForStringSet]失败,请联系管理员");
        return jedisReturnVO;
    }
}
