package com.example.webone.demo.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhx
 * @date 2018/12/12
 */
@RestController
@RequestMapping("zuulTest")
public class ZuulController {

    @GetMapping(value = "/zuulOpt")
    public String zuulOpt(){
        System.out.println("==========");
        return "zuul路由转发[webOne]";
    }

}
