package cn.camio1945;

import java.io.Serializable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动器
 *
 * @author Camio1945
 */
@SpringBootApplication(proxyBeanMethods = false)
@EnableScheduling
public class Application implements Serializable {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
