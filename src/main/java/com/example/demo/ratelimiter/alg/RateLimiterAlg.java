package com.example.demo.ratelimiter.alg;

import com.example.demo.ratelimiter.exception.InternalErrorException;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定时间窗口限流算法,固定1s内
 * @author cong.pi
 * @date 2022/4/7 16:32
 */
public class RateLimiterAlg {

  /* timeout for {@code Lock.tryLock() }. */
  private static final long TRY_LOCK_TIMEOUT = 200L;
  private Stopwatch stopWatch;
  private AtomicInteger currentCount = new AtomicInteger(0);
  private final int limit;
  private Lock lock = new ReentrantLock();

  public RateLimiterAlg(int limit) {
    this(limit, Stopwatch.createStarted());
  }

  @VisibleForTesting
  protected RateLimiterAlg(int limit, Stopwatch stopWatch) {
    this.limit = limit;
    this.stopWatch = stopWatch;
  }

  public boolean tryAcquire() throws InternalErrorException {
    int updatedCount = currentCount.incrementAndGet();
    if (updatedCount <= limit) {
      // 没达到限流
      return true;
    }

    // 如果updatedCount > limit
    try {
      if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
        try {
          // stopWatch刚刚创建或者reset之后默认是0，elapsed是计算从上次创建到当前过去了多少毫秒，如果 > 1000就reset并重置当前的限流计数
          if (stopWatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
            currentCount.set(0);
            stopWatch.reset();
            stopWatch.start();
          }
          updatedCount = currentCount.incrementAndGet();
          return updatedCount <= limit;
        } finally {
          lock.unlock();
        }
      } else {
        throw new InternalErrorException("tryAcquire() wait lock too long:" + TRY_LOCK_TIMEOUT + "ms");
      }
    } catch (InterruptedException e) {
      throw new InternalErrorException("tryAcquire() is interrupted by lock-time-out.", e);
    }
  }
}
