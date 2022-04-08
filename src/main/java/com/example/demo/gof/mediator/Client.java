package com.example.demo.gof.mediator;

/**
 * @author cong.pi
 * @date 2022/3/30 14:19
 */
public class Client {

  public static void main(String[] args) {
    Champions champions = new Champions();
    Wudang wudang = new Wudang(champions);
    Shaolin shaolin = new Shaolin(champions);
    Emei emei = new Emei(champions);
    champions.setWudang(wudang);
    champions.setShaolin(shaolin);
    champions.setEmei(emei);
    wudang.sendAlliance("武当弟子被少林大力金刚指所杀");
    emei.sendAlliance("峨眉弟子被山林大力金刚指所杀");
    shaolin.sendAlliance("少林弟子绝不会做出这种事情");
  }

}
