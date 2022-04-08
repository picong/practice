package com.example.demo.netty.the.flash.protocol.command;


import com.example.demo.netty.the.flash.serialize.Serializer;
import com.example.demo.netty.the.flash.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

public class PacketCodeCTest {

  @Test
  public void encode() {

    Serializer serializer = new JSONSerializer();
    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
    loginRequestPacket.setVersion((byte) 1);
    loginRequestPacket.setUserId(123);
    loginRequestPacket.setUsername("zhangsan");
    loginRequestPacket.setPassword("1234567");

    PacketCodeC packetCodeC = new PacketCodeC();
    ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);

    Packet decodePacket = packetCodeC.decode(byteBuf);

    Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodePacket));
  }

}