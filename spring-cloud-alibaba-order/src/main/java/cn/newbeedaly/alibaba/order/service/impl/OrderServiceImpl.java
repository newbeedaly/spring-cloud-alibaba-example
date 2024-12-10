package cn.newbeedaly.alibaba.order.service.impl;

import cn.newbeedaly.alibaba.order.adaptor.feign.CommodityFeignClient;
import cn.newbeedaly.alibaba.order.adaptor.feign.commodity.Commodity;
import cn.newbeedaly.alibaba.order.dao.OrderDao;
import cn.newbeedaly.alibaba.order.dao.po.Order;
import cn.newbeedaly.alibaba.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Primary
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderDao orderDao;
    @Autowired
    private CommodityFeignClient commodityFeignClient;

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order create(Long userId, Long commodityId, Integer num) {
        // 查询商品
        Commodity commodity = commodityFeignClient.getCommodityById(1L);
        if (commodity.getId() == -1) {
            throw new RuntimeException("查询商品失败");
        }
        // 保存订单信息
        Order order = new Order();
        order.setId(System.currentTimeMillis());
        order.setUserId(userId);
        order.setCommodityId(commodityId);
        order.setNum(num);
        orderDao.save(order);
        // 扣减库存, 模拟失败
        if(num==1) {
            commodityFeignClient.reduceCommodityStock(commodityId, num);
        }
        return order;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.getById(id);
    }
}
