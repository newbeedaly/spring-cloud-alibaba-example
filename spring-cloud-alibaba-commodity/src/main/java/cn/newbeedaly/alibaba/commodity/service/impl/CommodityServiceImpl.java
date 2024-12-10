package cn.newbeedaly.alibaba.commodity.service.impl;

import cn.newbeedaly.alibaba.commodity.dao.CommodityDao;
import cn.newbeedaly.alibaba.commodity.dao.po.Commodity;
import cn.newbeedaly.alibaba.commodity.service.ICommodityService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Primary
@Service
public class CommodityServiceImpl implements ICommodityService {

    @Resource
    private CommodityDao commodityDao;

    @Override
    public Commodity getCommodityById(Long id) {
        return commodityDao.getById(id);
    }

}
