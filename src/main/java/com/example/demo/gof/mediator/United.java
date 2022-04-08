package com.example.demo.gof.mediator;

/**
 * 抽象同事类(武林门派)
 * @author cong.pi
 * @date 2022/3/30 14:08
 */
public abstract class United {
  protected WulinAlliance wulinAlliance;

  public United(WulinAlliance wulinAlliance) {
    this.wulinAlliance = wulinAlliance;
  }
}
