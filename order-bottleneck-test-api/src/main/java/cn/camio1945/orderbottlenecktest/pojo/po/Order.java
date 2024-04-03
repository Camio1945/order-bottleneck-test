package cn.camio1945.orderbottlenecktest.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单
 *
 * @author Camio1945
 */
@Data
@TableName("`order`")
public class Order {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @TableField("sn")
  private String sn;

  @TableField("user_id")
  private Long userId;

  @TableField("name")
  private String name;

  @TableField("shipping_mobile")
  private String shippingMobile;

  @TableField("shipping_name")
  private String shippingName;

  @TableField("shipping_detail")
  private String shippingDetail;

  @TableField("total_amount")
  private BigDecimal totalAmount;

  @TableField("status")
  private Integer status;

  @TableField("add_date_hour")
  private String addDateHour;

  @TableField("add_minute_second")
  private String addMinuteSecond;
}
