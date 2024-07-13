package cn.camio1945.orderbottlenecktest.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品
 *
 * @author Camio1945
 */
@TableName("\"private\".\"goods\"")
public class Goods {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Boolean getOn() {
    return isOn;
  }

  public void setOn(Boolean on) {
    isOn = on;
  }

  public String getFirstImg() {
    return firstImg;
  }

  public void setFirstImg(String firstImg) {
    this.firstImg = firstImg;
  }

  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public LocalDateTime getAddTime() {
    return addTime;
  }

  public void setAddTime(LocalDateTime addTime) {
    this.addTime = addTime;
  }
}
