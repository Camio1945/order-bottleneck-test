package cn.camio1945.orderbottlenecktest.controller;

import cn.camio1945.orderbottlenecktest.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单控制器
 *
 * @author Camio1945
 */
@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired private IOrderService orderService;

  @PostMapping("/resetDb")
  public boolean resetDb() {
    orderService.resetDb();
    return true;
  }

  @PostMapping("/submitRandomOrder")
  public boolean submitRandomOrder() {
    return orderService.submitRandomOrder();
  }

  @GetMapping("/checkDataConsistency")
  public boolean check() {
    return orderService.checkDataConsistency();
  }
}
