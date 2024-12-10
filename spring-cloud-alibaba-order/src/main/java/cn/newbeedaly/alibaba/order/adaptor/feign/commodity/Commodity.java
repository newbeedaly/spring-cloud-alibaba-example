package cn.newbeedaly.alibaba.order.adaptor.feign.commodity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Commodity implements Serializable {

    private Integer id;
    private String name;
    private BigDecimal price;

}
