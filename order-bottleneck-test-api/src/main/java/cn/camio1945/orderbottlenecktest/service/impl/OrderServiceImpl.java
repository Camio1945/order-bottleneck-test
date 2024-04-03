package cn.camio1945.orderbottlenecktest.service.impl;

import cn.camio1945.orderbottlenecktest.constant.*;
import cn.camio1945.orderbottlenecktest.mapper.*;
import cn.camio1945.orderbottlenecktest.pojo.po.*;
import cn.camio1945.orderbottlenecktest.service.IOrderService;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 订单服务
 *
 * @author Camio1945
 */
@Service
public class OrderServiceImpl implements IOrderService {
  @Autowired private GoodsMapper goodsMapper;
  @Autowired private OrderMapper orderMapper;
  @Autowired private OrderItemMapper orderItemMapper;
  @Autowired private RedisTemplate<String, Object> redisTemplate;
  private static final String GOODS_STOCK_PREFIX = "cache:goods:id:stock";

  @Override
  public boolean resetDb() {
    // 注：tidb 版本不会清空数据库，只会还原库存
    // 还原库存
    List<Goods> goodsList = goodsMapper.selectList(null);
    LambdaUpdateWrapper<Goods> wrapper =
        new LambdaUpdateWrapper<Goods>().set(Goods::getStock, GoodsConstant.INIT_STOCK);
    goodsMapper.update(wrapper);
    GoodsConstant.GOODS_LIST.clear();
    GoodsConstant.GOODS_LIST.addAll(goodsList);
    // 更新缓存中的库存
    for (Goods goods : goodsList) {
      String key = CharSequenceUtil.format("{}::{}", GOODS_STOCK_PREFIX, goods.getId());
      redisTemplate.opsForValue().set(key, GoodsConstant.INIT_STOCK);
    }
    return true;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean submitRandomOrder() {
    List<OrderItem> orderItems = updateStockAndBuildOrderItems();
    Order order = buildOrder(orderItems);
    orderMapper.insert(order);
    orderItems.forEach(orderItem -> orderItem.setOrderId(order.getId()));
    orderItemMapper.insertBatch(orderItems);
    return true;
  }

  @Override
  public boolean checkDataConsistency() {
    syncStockFromRedisToDb();
    List<Goods> goodsList = goodsMapper.selectList(null);
    // 检查库存 >= 0
    int totalDecreasedStock = 0;
    for (Goods goods : goodsList) {
      Assert.isTrue(goods.getStock() >= 0, "库存小于 0 ：" + goods.getName());
      totalDecreasedStock += GoodsConstant.INIT_STOCK - goods.getStock();
    }
    int totalGoodsCount = orderItemMapper.selectTotalGoodsCount();
    Assert.isTrue(totalDecreasedStock == totalGoodsCount, "库存不一致");
    return true;
  }

  @Override
  @Scheduled(fixedRate = 1000)
  public synchronized void syncStockFromRedisToDb() {
    ValueOperations<String, Object> operations = redisTemplate.opsForValue();
    for (Goods goods : GoodsConstant.GOODS_LIST) {
      String key = CharSequenceUtil.format("{}::{}", GOODS_STOCK_PREFIX, goods.getId());
      int stock = (Integer) operations.get(key);
      goodsMapper.updateStock(goods.getId(), stock);
    }
  }

  private List<OrderItem> updateStockAndBuildOrderItems() {
    // 假设用户有 80% 的概率只买一件商品
    int itemsCount;
    if ((System.currentTimeMillis() % 10) < 8) {
      itemsCount = 1;
    } else {
      itemsCount = CommonConstant.RANDOM.nextInt(2, GoodsConstant.GOODS_LIST.size() + 1);
    }
    List<OrderItem> orderItems = new ArrayList<>(itemsCount);
    for (int i = 0; i < itemsCount; i++) {
      Goods goods = GoodsConstant.GOODS_LIST.get(i);
      OrderItem orderItem = new OrderItem();
      orderItem.setGoodsId(goods.getId());
      orderItem.setGoodsCount(i + 1);
      orderItem.setGoodsPrice(goods.getPrice());
      orderItem.setGoodsImg(goods.getFirstImg());
      Integer goodsCount = orderItem.getGoodsCount();
      BigDecimal totalAmount = goods.getPrice().multiply(new BigDecimal(goodsCount));
      orderItem.setTotalAmount(totalAmount);
      orderItems.add(orderItem);
      // 减库存（从 Redis 中操作）
      String key = CharSequenceUtil.format("{}::{}", GOODS_STOCK_PREFIX, goods.getId());
      synchronized (goods) {
        int currentStock = (int) redisTemplate.opsForValue().get(key);
        Assert.isTrue(currentStock >= goodsCount, "库存不足：" + goods.getName());
        redisTemplate.opsForValue().set(key, currentStock - goodsCount);
      }
    }
    return orderItems;
  }

  private Order buildOrder(List<OrderItem> orderItems) {
    Order order = new Order();
    order.setUserId(1L);
    order.setSn(UUID.randomUUID().toString());
    order.setName("张三");
    order.setShippingMobile("12345678901");
    order.setShippingName("张三");
    order.setShippingDetail("广东省深圳市南山区某某街道某某小区某某单元某某室");
    orderItems.stream()
        .map(OrderItem::getTotalAmount)
        .reduce(BigDecimal::add)
        .ifPresent(order::setTotalAmount);
    order.setStatus(OrderConstant.Status.UNPAID);
    LocalDateTime now = LocalDateTime.now();
    String nowStr = now.toString();
    String addDateHour = nowStr.substring(0, 13).replace("-", "").replace("T", "");
    order.setAddDateHour(addDateHour);
    order.setAddMinuteSecond(nowStr.substring(14, 19).replace(":", ""));
    return order;
  }
}
