package cn.camio1945.orderbottlenecktest.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品
 *
 * @author Camio1945
 */
@Data
@TableName("goods")
public class Goods {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @TableField("name")
  private String name;

  @TableField("price")
  private BigDecimal price;

  @TableField("stock")
  private Integer stock;

  @TableField("is_on")
  private Boolean isOn;

  @TableField("first_img")
  private String firstImg;

  @TableField("intro")
  private String intro;

  @TableField("add_time")
  private LocalDateTime addTime;
}
