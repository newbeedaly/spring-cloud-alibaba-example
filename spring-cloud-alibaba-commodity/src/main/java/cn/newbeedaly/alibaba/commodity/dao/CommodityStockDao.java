package cn.newbeedaly.alibaba.commodity.dao;

import cn.newbeedaly.alibaba.commodity.dao.mapper.CommodityStockMapper;
import cn.newbeedaly.alibaba.commodity.dao.po.CommodityStock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CommodityStockDao extends ServiceImpl<CommodityStockMapper, CommodityStock> implements IService<CommodityStock> {

}
