package com.example.demo.chain;

/**
 * @author cong.pi
 * @date 2022/3/8 19:32
 */
public class HandlerB extends Handler{


  @Override
  protected boolean doHandle() {
    System.out.println("hanle B");
    return false;
  }
}
