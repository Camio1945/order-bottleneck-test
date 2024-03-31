package cn.camio1945.common;

import java.time.Duration;
import java.util.Random;

import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

/**
 * 用于生成带随机偏移量的 TTL 时间，比如原先设置的是 60 秒，那么实际过期时间将在 [60,66] 秒之间，以解决缓存雪崩问题。
 *
 * @param duration
 */
public record RandomOffsetTtlFunction(Duration duration) implements RedisCacheWriter.TtlFunction {
  private static final Random RANDOM = new Random();

  @Override
  public Duration getTimeToLive(Object key, @Nullable Object value) {
    long seconds = this.duration.getSeconds();
    // 10% offset
    long offset = RANDOM.nextLong(0, (seconds / 10) + 1);
    return Duration.ofSeconds(seconds + offset);
  }
}
