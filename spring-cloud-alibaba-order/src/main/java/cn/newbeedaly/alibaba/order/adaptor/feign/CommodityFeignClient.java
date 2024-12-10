package cn.newbeedaly.alibaba.order.adaptor.feign;

import cn.newbeedaly.alibaba.order.adaptor.feign.commodity.Commodity;
import cn.newbeedaly.alibaba.order.adaptor.feign.config.FeignConfiguration;
import cn.newbeedaly.alibaba.order.adaptor.feign.fallback.CommodityFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "commodity", contextId = "CommodityFeignClient", fallback = CommodityFeignClientFallBack.class, configuration = FeignConfiguration.class)
public interface CommodityFeignClient {

    @GetMapping("/commodity/{id}")
    public Commodity getCommodityById(@PathVariable("id") Long id);

    @PostMapping("/commodity/reduceStock")
    public Boolean reduceCommodityStock(@RequestParam("id") Long id, @RequestParam("num") Integer num);

}
