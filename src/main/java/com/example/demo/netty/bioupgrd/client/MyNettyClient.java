package com.example.demo.netty.bioupgrd.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyNettyClient {

  private static final StringDecoder DECODER = new StringDecoder();
  private static final StringEncoder ENCODER = new StringEncoder();
  private static final ClientHandler CLIENT_HANDLER = new ClientHandler();

  public static void main(String[] args) {
    // 创建时间循环线程组（客户端只有一个）
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(group)
          .channel(NioSocketChannel.class)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
                    // 消息解码： 读取消息头和消息体
                    .addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4))
                    // 消息编码：将消息头和消息体，在响应字节数据前面添加消息体长度
                    .addLast(new LengthFieldPrepender(4))
                    .addLast(DECODER)
                    .addLast(ENCODER)
                    .addLast(CLIENT_HANDLER);
            }
          });
      // 连接服务器端并同步通道
      Channel channel = bootstrap.connect("127.0.0.1", 8007).sync().channel();

      // 发送消息
      ChannelFuture lastWriteFuture = null;
      for (int i = 0; i < 10; i++) {
        lastWriteFuture = channel.writeAndFlush("Hi, Java\n");
      }
      // 在通道关闭前，同步刷新所有的消息
      if (lastWriteFuture != null) {
        lastWriteFuture.sync();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      group.shutdownGracefully();
    }
  }

  static class ClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
      System.out.println("接收到服务端的消息:" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ctx.close();
    }
  }
}
