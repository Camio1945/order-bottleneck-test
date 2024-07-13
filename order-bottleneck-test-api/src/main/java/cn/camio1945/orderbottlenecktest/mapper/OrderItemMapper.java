package cn.camio1945.orderbottlenecktest.mapper;

import cn.camio1945.orderbottlenecktest.pojo.po.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * 订单项数据库操作
 *
 * @author Camio1945
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

  /**
   * 批量插入
   *
   * @param orderItems 订单项列表
   */
  @Insert(
      """
        <script>
          INSERT INTO \"private\".\"order_item\" (\"order_id\", \"goods_id\", \"goods_count\", \"goods_price\", \"goods_img\", \"total_amount\")
          VALUES
          <foreach collection="orderItems" item="item" separator="),(" open="(" close=")">
            #{item.orderId}, #{item.goodsId}, #{item.goodsCount}, #{item.goodsPrice}, #{item.goodsImg}, #{item.totalAmount}
          </foreach>
        </script>
      """)
  void insertBatch(List<OrderItem> orderItems);

  /** 截断表 */
  @Update("TRUNCATE \"private\".\"order_item\"")
  void truncate();

  /**
   * 查询商品总数
   *
   * @return 商品总数
   */
  @Select("SELECT COALESCE(SUM(\"goods_count\"),0) FROM \"private\".\"order_item\"")
  int selectTotalGoodsCount();
}
