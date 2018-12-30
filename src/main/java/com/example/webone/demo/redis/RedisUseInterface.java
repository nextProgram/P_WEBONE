package com.example.webone.demo.redis;

import com.example.webone.demo.base.ReturnVO;
import com.example.webone.demo.redis.vo.JedisRequestVO;
import com.example.webone.demo.redis.vo.JedisReturnVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lhx
 * @date 2018/12/12
 */
@FeignClient(name = "redis")
public interface RedisUseInterface {
    @PostMapping(value = "/jedisStr/jedisForStringSet")
    ReturnVO<JedisReturnVO> jedisForStringSet(JedisRequestVO jedisRequestVO);
}
