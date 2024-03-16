package cn.camio1945.orderbottlenecktest.constant;

import cn.camio1945.orderbottlenecktest.listener.ApplicationEventListener;
import cn.camio1945.orderbottlenecktest.pojo.po.Goods;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品常量
 *
 * @author Camio1945
 */
public interface GoodsConstant {
  /** 在 {@link ApplicationEventListener } 中初始化 */
  List<Goods> GOODS_LIST = new ArrayList<>();

  /** 初始库存 */
  int INIT_STOCK = 100001;
}
