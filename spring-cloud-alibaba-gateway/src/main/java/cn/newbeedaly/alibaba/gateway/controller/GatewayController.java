package cn.newbeedaly.alibaba.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}

