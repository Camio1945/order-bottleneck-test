package cn.camio1945.orderbottlenecktest.mapper;

import cn.camio1945.orderbottlenecktest.pojo.po.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 商品数据库操作
 *
 * @author Camio1945
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

  /**
   * 减库存
   *
   * @param goodsId 商品id
   * @param stock 库存
   * @return 影响行数
   */
  @Update(
      """
          UPDATE `goods`
            SET `stock` = #{stock}
          WHERE `id` = #{goodsId}
          """)
  int updateStock(Integer goodsId, int stock);

  /**
   * 减库存
   *
   * @param goodsId 商品id
   * @param decreaseCount 减少数量
   * @return 影响行数
   */
  @Update(
      """
          UPDATE `goods`
            SET `stock` = `stock` - #{decreaseCount}
          WHERE `id` = #{goodsId}
            AND `stock` >= #{decreaseCount}
          """)
  int decreaseStock(Integer goodsId, int decreaseCount);
}
