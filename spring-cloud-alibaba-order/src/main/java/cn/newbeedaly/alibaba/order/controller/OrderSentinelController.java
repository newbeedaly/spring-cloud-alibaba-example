package cn.newbeedaly.alibaba.order.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderSentinelController {

    @GetMapping("/order/sentinel1/1")
    public String sentinel1(){
        return "1";
    }

    @GetMapping("/order/sentinel2/2")
    public String sentinel2(){
        return "2";
    }

}
