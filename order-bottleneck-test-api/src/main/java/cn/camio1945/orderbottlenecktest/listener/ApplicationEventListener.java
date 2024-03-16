package cn.camio1945.orderbottlenecktest.listener;

import cn.camio1945.orderbottlenecktest.constant.GoodsConstant;
import cn.camio1945.orderbottlenecktest.mapper.*;
import cn.camio1945.orderbottlenecktest.pojo.po.Goods;
import cn.camio1945.orderbottlenecktest.service.IOrderService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Service;

/**
 * 应用事件监听器
 *
 * @author HuKaiXuan
 */
@Service
public class ApplicationEventListener implements ApplicationListener {
  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(ApplicationEventListener.class);

  @Autowired private IOrderService orderService;

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    // 项目启动完成事件
    if (event instanceof ApplicationReadyEvent) {
      LOGGER.info("项目启动完成，监听器 {} 开始执行", ApplicationEventListener.class);
      try {
        orderService.resetDb();
      } catch (Exception e) {
        LOGGER.error("项目启动完成事件异常", e);
      }
      return;
    }
    // 项目停止和应用关闭事件
    if ((event instanceof ContextStoppedEvent) || (event instanceof ContextClosedEvent)) {
      LOGGER.info("项目停止和应用关闭事件");
    }
  }
}
