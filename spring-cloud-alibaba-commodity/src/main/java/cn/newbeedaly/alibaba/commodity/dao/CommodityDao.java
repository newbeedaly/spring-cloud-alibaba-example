package cn.newbeedaly.alibaba.commodity.dao;

import cn.newbeedaly.alibaba.commodity.dao.mapper.CommodityMapper;
import cn.newbeedaly.alibaba.commodity.dao.po.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CommodityDao extends ServiceImpl<CommodityMapper, Commodity> implements IService<Commodity> {

}
