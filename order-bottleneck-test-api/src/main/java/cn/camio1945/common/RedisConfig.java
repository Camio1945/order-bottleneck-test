package cn.camio1945.common;

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.lettuce.core.ReadFrom;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

/**
 * Redis 配置
 *
 * @author Camio1945
 */
@Slf4j
@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisConfig implements CachingConfigurer {

  /**
   * 这个对象是从 application.yml 中读取，然后注入到当前类中的 <br>
   * 因为当前类 RedisSingleConfig 上有 @Configuration 注解 <br>
   * 然后 RedisProperties 类上有 @ConfigurationProperties(prefix = "spring.data.redis") 注解 <br>
   */
  private final RedisProperties redisProperties;

  @Value("${spring.cache.redis.time-to-live}")
  private long redisTimeToLive;

  @Value("${spring.data.redis.timeout}")
  private Duration redisCommandTimeout;

  /**
   * 创建 RedisTemplate Bean，使用 JSON 序列化方式，来自：<a
   * href="https://github.com/YunaiV/yudao-cloud/blob/3a25879064279ea478c5efc09b629ffc632b04da/yudao-framework/yudao-spring-boot-starter-redis/src/main/java/cn/iocoder/yudao/framework/redis/config/YudaoRedisAutoConfiguration.java#L16">yudao-cloud</a>
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    // 创建 RedisTemplate 对象
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    // 设置 RedisConnection 工厂。😈 它就是实现多种 Java Redis 客户端接入的秘密工厂。感兴趣的胖友，可以自己去撸下。
    template.setConnectionFactory(factory);
    // 使用 String 序列化方式，序列化 KEY 。
    template.setKeySerializer(RedisSerializer.string());
    template.setHashKeySerializer(RedisSerializer.string());
    // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
    RedisSerializer<?> serializer = buildRedisSerializer();
    template.setValueSerializer(serializer);
    template.setHashValueSerializer(serializer);
    return template;
  }

  public static RedisSerializer<Object> buildRedisSerializer() {
    RedisSerializer<Object> json = RedisSerializer.json();
    // 解决 LocalDateTime 的序列化
    ObjectMapper objectMapper = (ObjectMapper) ReflectUtil.getFieldValue(json, "mapper");
    objectMapper.registerModules(new JavaTimeModule());
    return json;
  }

  @Bean
  protected LettuceConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration serverConfig =
        new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
    serverConfig.setPassword(redisProperties.getPassword());
    serverConfig.setDatabase(redisProperties.getDatabase());
    LettuceClientConfiguration clientConfig =
        LettuceClientConfiguration.builder()
            .commandTimeout(redisCommandTimeout)
            // 优先读取从库
            .readFrom(ReadFrom.REPLICA_PREFERRED)
            .build();
    return new LettuceConnectionFactory(serverConfig, clientConfig);
  }

  @Override
  @Bean
  public RedisCacheManager cacheManager() {
    return RedisCacheManager.builder(this.redisConnectionFactory())
        .cacheDefaults(this.cacheConfiguration())
        .build();
  }

  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(new RandomOffsetTtlFunction(Duration.ofMinutes(redisTimeToLive)))
        .serializeValuesWith(
            SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  }
}
