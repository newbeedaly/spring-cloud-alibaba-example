package cn.newbeedaly.alibaba.order.adaptor.feign.fallback;

import cn.newbeedaly.alibaba.order.adaptor.feign.CommodityFeignClient;
import cn.newbeedaly.alibaba.order.adaptor.feign.commodity.Commodity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommodityFeignClientFallBack implements CommodityFeignClient {

    @Override
    public Commodity getCommodityById(Long id) {
        Commodity commodity = new Commodity();
        commodity.setId(-1);
        return commodity;
    }

    @Override
    public Boolean reduceCommodityStock(Long id, Integer num) {
        log.info("商品 {} 库存扣减失败, 数量: {}", id, num);
        throw new RuntimeException("商品库存扣减失败");
    }

}
