package cn.camio1945.common;

import cn.camio1945.Application;
import cn.camio1945.orderbottlenecktest.listener.ApplicationEventListener;
import cn.camio1945.orderbottlenecktest.service.impl.OrderServiceImpl;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeSerialization;

/**
 * lambda 表达式注入到 graal 中
 *
 * @author ztp
 * @date 2023/8/18 11:53
 */
public class LambdaRegistrationFeature implements Feature {

  @Override
  public void duringSetup(DuringSetupAccess access) {
    // TODO 这里需要将lambda表达式所使用的成员类都注册上来,具体情况视项目情况而定,一般扫描@Controller和@Service的会多点.
    RuntimeSerialization.registerLambdaCapturingClass(Application.class);
    RuntimeSerialization.registerLambdaCapturingClass(ApplicationEventListener.class);
    RuntimeSerialization.registerLambdaCapturingClass(MyBatisNativeConfiguration.class);
    RuntimeSerialization.registerLambdaCapturingClass(OrderServiceImpl.class);
  }
}
