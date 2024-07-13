package cn.camio1945.orderbottlenecktest.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author Camio1945
 */
@TableName("\"private\".\"order\"")
public class Order {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @TableField("sn")
  private String sn;

  @TableField("user_id")
  private Integer userId;

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

  @TableField("add_time")
  private LocalDateTime addTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSn() {
    return sn;
  }

  public void setSn(String sn) {
    this.sn = sn;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShippingMobile() {
    return shippingMobile;
  }

  public void setShippingMobile(String shippingMobile) {
    this.shippingMobile = shippingMobile;
  }

  public String getShippingName() {
    return shippingName;
  }

  public void setShippingName(String shippingName) {
    this.shippingName = shippingName;
  }

  public String getShippingDetail() {
    return shippingDetail;
  }

  public void setShippingDetail(String shippingDetail) {
    this.shippingDetail = shippingDetail;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public LocalDateTime getAddTime() {
    return addTime;
  }

  public void setAddTime(LocalDateTime addTime) {
    this.addTime = addTime;
  }
}
