package cn.newbeedaly.alibaba.order.service;


import cn.newbeedaly.alibaba.order.dao.po.Order;

public interface IOrderService {

    Order create(Long userId, Long commodityId, Integer num);

    Order getOrderById(Long id);

}
