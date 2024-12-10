package cn.newbeedaly.alibaba.order.controller;

import cn.newbeedaly.alibaba.order.dao.po.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderMQController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/order/mq/{id}")
    public Order order(@PathVariable("id") Long id) {
        Order order = new Order();
        order.setId(id);
        order.setCommodityId(1L);
        order.setNum(1);
        order.setUserId(1L);
        // 下单成功，将消息发送到MQ中
        rocketMQTemplate.convertAndSend("order-topic", order);
        return order;
    }
}
