package com.example.demo.netty.the.flash.protocol.command;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public abstract class Packet {

  /**
   * 版本协议
   */
  @JSONField(deserialize = false, serialize = false)
  private Byte version = 1;

  @JSONField(serialize = false)
  public abstract Byte getCommand();

}
