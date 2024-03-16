package cn.camio1945.orderbottlenecktest.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import java.math.BigDecimal;

/**
 * 订单项
 *
 * @author Camio1945
 */
@TableName("order_item")
public class OrderItem {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @TableField("order_id")
  private Integer orderId;

  @TableField("goods_id")
  private Integer goodsId;

  @TableField("goods_count")
  private Integer goodsCount;

  @TableField("goods_price")
  private BigDecimal goodsPrice;

  @TableField("goods_img")
  private String goodsImg;

  @TableField("total_amount")
  private BigDecimal totalAmount;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Integer goodsId) {
    this.goodsId = goodsId;
  }

  public Integer getGoodsCount() {
    return goodsCount;
  }

  public void setGoodsCount(Integer goodsCount) {
    this.goodsCount = goodsCount;
  }

  public BigDecimal getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(BigDecimal goodsPrice) {
    this.goodsPrice = goodsPrice;
  }

  public String getGoodsImg() {
    return goodsImg;
  }

  public void setGoodsImg(String goodsImg) {
    this.goodsImg = goodsImg;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }
}
