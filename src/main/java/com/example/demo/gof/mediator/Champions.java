package com.example.demo.gof.mediator;

/**
 * @author cong.pi
 * @date 2022/3/30 14:15
 */
public class Champions extends WulinAlliance {

  private Wudang wudang;
  private Shaolin shaolin;
  private Emei emei;

  public void setWudang(Wudang wudang) {
    this.wudang = wudang;
  }

  public void setShaolin(Shaolin shaolin) {
    this.shaolin = shaolin;
  }

  public void setEmei(Emei emei) {
    this.emei = emei;
  }

  @Override
  public void notice(String message, United united) {
    if (united == wudang) {
      shaolin.getNotice(message);
    } else if (united == emei) {
      shaolin.getNotice(message);
    } else if (united == shaolin) {
      wudang.getNotice(message);
      emei.getNotice(message);
    }
  }
}
