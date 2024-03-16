package cn.camio1945.orderbottlenecktest.mapper;

import cn.camio1945.orderbottlenecktest.pojo.po.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 订单数据库操作
 *
 * @author Camio1945
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

  /** 截断表 */
  @Update("TRUNCATE `order`")
  void truncate();
}
