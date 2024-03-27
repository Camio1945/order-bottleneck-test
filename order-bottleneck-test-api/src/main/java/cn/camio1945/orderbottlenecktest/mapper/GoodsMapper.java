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
   * 修改库存
   *
   * @param goodsId 商品id
   * @param stock 库存
   */
  @Update("ALTER TABLE `goods` UPDATE `stock` = #{stock} WHERE `id` = #{goodsId}")
  void updateStock(Integer goodsId, int stock);

  /**
   * 减库存
   *
   * @param goodsId 商品id
   * @param decreaseCount 减少数量
   */
  @Update("""
        ALTER TABLE `goods` 
        UPDATE `stock` = stock - #{decreaseCount} 
        WHERE `id` = #{goodsId} 
        AND stock >= #{decreaseCount}
      """)
  void decreaseStock(Integer goodsId, int decreaseCount);
}
