package com.example.webone.demo.webTwo;

import com.example.webone.demo.base.service.FallBackOfJedis;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lhx
 * @date 2019/4/1
 */
@FeignClient(name = "web-two")
public interface webTwoInterface {

}
