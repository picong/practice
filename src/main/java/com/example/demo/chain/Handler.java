package com.example.demo.chain;

/**
 * @author cong.pi
 * @date 2022/3/8 19:31
 */
public abstract class Handler {

  protected Handler successor = null;

  public void setSuccessor(Handler successor) {
    this.successor = successor;
  }

  public final void handle() {
    boolean handled = doHandle();
    if (successor != null && !handled) {
      successor.handle();
    }
  }

  protected abstract boolean doHandle();
}
