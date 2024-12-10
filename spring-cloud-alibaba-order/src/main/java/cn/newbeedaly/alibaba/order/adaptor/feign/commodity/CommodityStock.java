package cn.newbeedaly.alibaba.order.adaptor.feign.commodity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommodityStock implements Serializable {

    private Long id;
    private Long commodityId;
    private Integer stock;

}
