package cn.newbeedaly.alibaba.order.controller;

import cn.newbeedaly.alibaba.order.dao.po.Order;
import cn.newbeedaly.alibaba.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/{id}")
    public Order order(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/create")
    public Order createOrder(@RequestParam Long userId, @RequestParam Long commodityId, @RequestParam Integer num) {
        return orderService.create(userId, commodityId, num);
    }
}
