package com.example.demo.eventbus;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author cong.pi
 * @date 2022/3/7 17:38
 */
public class ObserverAction {

  private Object target;
  private Method method;

  public ObserverAction(Object target, Method method) {
    this.target = Preconditions.checkNotNull(target);
    this.method = method;
    this.method.setAccessible(true);
  }

  /**
   * event是method方法中的参数
   * @param event
   */
  public void execute(Object event) {
    try {
      method.invoke(target, event);
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}
