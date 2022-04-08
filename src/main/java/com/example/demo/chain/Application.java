package com.example.demo.chain;

/**
 * @author cong.pi
 * @date 2022/3/9 9:49
 */
public class Application {

  public static void main(String[] args) {
    HandlerChain chain = new HandlerChain();
    chain.addHandler(new HandlerA());
    chain.addHandler(new HandlerB());
    chain.handle();
  }

}
