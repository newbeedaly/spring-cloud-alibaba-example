package cn.newbeedaly.alibaba.commodity.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sca_cmm_commodity_stock")
public class CommodityStock {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品ID
     */
    @TableField("commodity_id")
    private Integer commodityId;
    /**
     * 数量
     */
    @TableField("stock")
    private Integer stock;

}
