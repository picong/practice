package com.example.demo.netty.bio.server;

import com.example.demo.netty.bio.packet.SocketPacket;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 服务端
 */
public class MySocketServer {

  public static void main(String[] args) throws IOException {
    // 创建 Socket 服务端
    ServerSocket serverSocket = new ServerSocket(9093);
    // 获取客户端连接
    Socket clientSocket = serverSocket.accept();
    // 使用线程池处理更多的客户端消息
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(100, 150, 100, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(1000));
    threadPool.submit(() -> {
      // 客户端消息处理
      processMessage(clientSocket);
    });
  }

  /**
   * 客户端消息处理
   * @param clientSocket
   */
  private static void processMessage(Socket clientSocket) {
    // Socket 封装对象
    SocketPacket socketPacket = new SocketPacket();
    // 获取客户端发送的消息对象
    try (InputStream inputstream = clientSocket.getInputStream()) {
      while (true) {
        // 获取消息头 （也就是消息体的长度）
        int bodyLength = socketPacket.getHeader(inputstream);
        // 消息体 byte 数组
        byte[] bodyBytes = new byte[bodyLength];
        // 每次实际读取字节数
        int readCount = 0;
        // 消息体复制下标
        int bodyIndex = 0;
        // 循环接收消息头中定义的长度
        while (bodyIndex <= (bodyLength - 1) &&
            (readCount = inputstream.read(bodyBytes, bodyIndex, bodyLength)) != -1) {
          bodyIndex += readCount;
        }
        bodyIndex = 0;
        // 成功接收到客户端的消息并打印
        System.out.println("接收到客户端的消息:" + new String(bodyBytes));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
