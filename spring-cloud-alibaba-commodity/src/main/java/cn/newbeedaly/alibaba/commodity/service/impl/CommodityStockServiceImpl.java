package cn.newbeedaly.alibaba.commodity.service.impl;

import cn.newbeedaly.alibaba.commodity.dao.CommodityStockDao;
import cn.newbeedaly.alibaba.commodity.dao.po.CommodityStock;
import cn.newbeedaly.alibaba.commodity.service.ICommodityStockService;
import com.google.common.base.Preconditions;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Primary
@Service
public class CommodityStockServiceImpl implements ICommodityStockService {

    @Resource
    private CommodityStockDao commodityStockDao;

    @Override
    public Boolean reduceStock(Long id, Integer num) {
        CommodityStock commodityStock = commodityStockDao.getById(id);
        Preconditions.checkArgument(commodityStock.getStock() >= num, "商品库存不足");
        commodityStock.setStock(commodityStock.getStock() - num);
        commodityStockDao.save(commodityStock);
        return true;
    }

    @Override
    public CommodityStock getCommodityStockByCommodityId(Long commodityId) {
        return commodityStockDao.lambdaQuery().eq(CommodityStock::getCommodityId, commodityId).one();
    }

}
