package cn.camio1945.orderbottlenecktest.constant;

/**
 * 订单常量
 *
 * @author Camio1945
 */
public interface OrderConstant {
  interface Status {
    int UNPAID = 1;
    int PAID = 2;
    int DELIVERED = 3;
    int FINISHED = 4;
  }
}
