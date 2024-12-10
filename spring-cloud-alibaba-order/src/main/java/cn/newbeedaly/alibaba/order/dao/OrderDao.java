package cn.newbeedaly.alibaba.order.dao;

import cn.newbeedaly.alibaba.order.dao.mapper.OrderMapper;
import cn.newbeedaly.alibaba.order.dao.po.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class OrderDao extends ServiceImpl<OrderMapper, Order> implements IService<Order> {

}
