package com.example.demo.eventbus;

import java.util.concurrent.Executor;

/**
 * @author cong.pi
 * @date 2022/3/7 18:14
 */
public class AsyncEventBus extends EventBus{

  public AsyncEventBus(Executor executor) {
    super(executor);
  }
}
