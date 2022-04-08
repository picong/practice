package com.example.demo.netty.bio.client;

import com.example.demo.netty.bio.packet.SocketPacket;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * 客户端
 */
public class MySocketClient {

  public static void main(String[] args) throws IOException {
    // 启动Socket 并尝试连接服务器
    Socket socket = new Socket("127.0.0.1", 9093);
    // 发送消息集合 （随机发送一条消息）
    final String[] messages = {"Hi,Java.", "Hi,Sql~","关注公众号|Java中文社群。"};
    // 创建协议封装对象
    SocketPacket socketPacket = new SocketPacket();
    try (OutputStream outputStream = socket.getOutputStream()) {
      // 给服务端发送10次消息
      for (int i = 0; i < 10; i++) {
        // 随机发送一条消息
        String msg = messages[new Random().nextInt(messages.length)];
        // 将内容封装为：协议头 + 协议体
        byte[] bytes = socketPacket.toBytes(msg);
        // 发送协消息
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
      }
    }
  }
}
