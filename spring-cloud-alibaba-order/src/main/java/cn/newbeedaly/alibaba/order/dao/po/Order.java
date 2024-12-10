package cn.newbeedaly.alibaba.order.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sca_ord_order")
public class Order {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 商品ID
     */
    @TableField("commodity_id")
    private Long commodityId;
    /**
     * 数量
     */
    @TableField("num")
    private Integer num;
}
