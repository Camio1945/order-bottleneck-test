package cn.camio1945.orderbottlenecktest.service;

/**
 * 订单服务
 *
 * @author Camio1945
 */
public interface IOrderService {

  /**
   * 重置数据库
   */
  boolean resetDb();

  /**
   * 随机下单
   *
   * @return 是否成功
   */
  boolean submitRandomOrder();

  /**
   * 检查数据一致性
   *
   * @return 是否一致
   */
  boolean checkDataConsistency();
}
