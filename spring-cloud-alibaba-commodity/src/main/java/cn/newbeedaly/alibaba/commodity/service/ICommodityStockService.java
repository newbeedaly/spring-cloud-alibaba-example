package cn.newbeedaly.alibaba.commodity.service;


import cn.newbeedaly.alibaba.commodity.dao.po.CommodityStock;

public interface ICommodityStockService {

    Boolean reduceStock(Long commodityId, Integer num);

    CommodityStock getCommodityStockByCommodityId(Long commodityId);

}
