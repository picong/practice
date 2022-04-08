package com.example.demo.gof.mediator;

/**
 * 具体同事类（峨眉派）
 * @author cong.pi
 * @date 2022/3/30 14:11
 */
public class Emei extends United {

  public Emei(WulinAlliance wulinAlliance) {
    super(wulinAlliance);
  }
  public void sendAlliance(String message) {
    wulinAlliance.notice(message, Emei.this);
  }

  public void getNotice(String message) {
    System.out.println("峨眉山收到消息：" + message);
  }
}
