package com.example.demo.netty.the.flash.protocol.command;

import static com.example.demo.netty.the.flash.protocol.command.Command.LOGIN_REQUEST;

import lombok.Data;

@Data
public class LoginRequestPacket extends Packet {

  private Integer userId;
  private String username;
  private String password;

  @Override
  public Byte getCommand() {
    return LOGIN_REQUEST;
  }
}
