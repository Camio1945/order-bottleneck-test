package cn.camio1945.orderbottlenecktest.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单项
 *
 * @author Camio1945
 */
@Data
@TableName("order_item")
public class OrderItem {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @TableField("order_id")
  private Long orderId;

  @TableField("goods_id")
  private Long goodsId;

  @TableField("goods_count")
  private Integer goodsCount;

  @TableField("goods_price")
  private BigDecimal goodsPrice;

  @TableField("goods_img")
  private String goodsImg;

  @TableField("total_amount")
  private BigDecimal totalAmount;
}
