package com.example.demo.ratelimiter;

import com.example.demo.ratelimiter.alg.RateLimiterAlg;
import com.example.demo.ratelimiter.exception.InternalErrorException;
import com.example.demo.ratelimiter.rule.ApiLimit;
import com.example.demo.ratelimiter.rule.RateLimiterRule;
import com.example.demo.ratelimiter.rule.RuleConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

/**
 * 限流集成类，组装功能，对外提供访问入口
 * @author cong.pi
 * @date 2022/4/7 16:18
 */
public class RateLimiter {

  private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);
  // 为每个api在内存中存储限流计数器
  private ConcurrentHashMap<String, RateLimiterAlg> counters = new ConcurrentHashMap<>();
  private RateLimiterRule rule;

  public RateLimiter() {
    // 将限流规则配置文件ratelimiter-rule.yaml 中的内容读取到RuleConfig中
    InputStream in = null;
    RuleConfig ruleConfig = null;
    try {
      in = this.getClass().getResourceAsStream("/ratelimiter-rule.yaml");
      if (in != null) {
        Yaml yaml = new Yaml();
        ruleConfig = yaml.loadAs(in, RuleConfig.class);
      }
    }finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          log.error("close file error: {}", e);
        }
      }
    }

    // 将限流规则构建成支持快速查找的数据结构RateLimitRule
    this.rule = new RateLimiterRule(ruleConfig);
  }

  public boolean limit(String appId, String url) throws InternalErrorException {
    ApiLimit apiLimit = rule.getApiLimit(appId, url);
    if (apiLimit == null) {
      return true;
    }

    // 获取api对应在内存中的限流计数器 (rateLimitCounter)
    String counterKey = appId + ":" + apiLimit.getApi();
    RateLimiterAlg rateLimiterCounter = counters.get(counterKey);
    if (rateLimiterCounter == null) {
      RateLimiterAlg newRateLimitCounter = new RateLimiterAlg(apiLimit.getLimit());
      rateLimiterCounter = counters.putIfAbsent(counterKey, newRateLimitCounter);
      if (rateLimiterCounter == null) {
        rateLimiterCounter = newRateLimitCounter;
      }
    }
    // 判断是否限流
    return rateLimiterCounter.tryAcquire();
  }
}
