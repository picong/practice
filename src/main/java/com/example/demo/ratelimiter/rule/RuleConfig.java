package com.example.demo.ratelimiter.rule;

import java.util.List;

/**
 *
 configs:          <!--对应RuleConfig-->
 - appId: app-1    <!--对应AppRuleConfig-->
 limits:
 - api: /v1/user <!--对应ApiLimit-->
 limit: 100
 unit：60
 - api: /v1/order
 limit: 50
 - appId: app-2
 limits:
 - api: /v1/user
 limit: 50
 - api: /v1/order
 limit: 50
 * @author cong.pi
 * @date 2022/4/7 16:23
 */
public class RuleConfig {

  private List<AppRuleConfig> configs;

  public List<AppRuleConfig> getConfigs() {
    return configs;
  }

  public void setConfigs(
      List<AppRuleConfig> configs) {
    this.configs = configs;
  }

  public static class AppRuleConfig {
    private String appId;
    private List<ApiLimit> limits;

    public AppRuleConfig() {}

    public AppRuleConfig(String appId,
        List<ApiLimit> limits) {
      this.appId = appId;
      this.limits = limits;
    }

    public String getAppId() {
      return appId;
    }

    public void setAppId(String appId) {
      this.appId = appId;
    }

    public List<ApiLimit> getLimits() {
      return limits;
    }

    public void setLimits(List<ApiLimit> limits) {
      this.limits = limits;
    }
  }

}
