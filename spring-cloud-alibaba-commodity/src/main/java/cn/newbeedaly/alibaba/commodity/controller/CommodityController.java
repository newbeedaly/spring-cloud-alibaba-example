package cn.newbeedaly.alibaba.commodity.controller;

import cn.newbeedaly.alibaba.commodity.dao.po.Commodity;
import cn.newbeedaly.alibaba.commodity.service.ICommodityService;
import cn.newbeedaly.alibaba.commodity.service.ICommodityStockService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/commodity")
@RestController
public class CommodityController {

    @Autowired
    private ICommodityService commodityService;
    @Autowired
    private ICommodityStockService commodityStockService;

    @GetMapping("/{id}")
    public Commodity getCommodityById(@PathVariable("id") Long id) {
        Commodity commodity = commodityService.getCommodityById(id);
        log.info("商品查询：{}", JSON.toJSONString(commodity));
        return commodity;
    }

    @PostMapping("/reduceStock")
    public Boolean reduceCommodityStock(@RequestParam("id") Long id, @RequestParam("num") Integer num) {
        log.info("扣减商品库存, 商品ID:{}, 库存数量:{}", id, num);
        commodityStockService.reduceStock(id, num);
        return true;
    }

}
