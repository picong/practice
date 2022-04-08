package com.example.demo.netty.bioupgrd.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyNettyServer {

  private static final StringDecoder DECODER = new StringDecoder();
  private static final StringEncoder ENCODER = new StringEncoder();
  private static final ServerHandler SERVER_HANDLER = new ServerHandler();
  private static final int PORT = 8007;

  public static void main(String[] args) {

    NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    NioEventLoopGroup wokerGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap sb = new ServerBootstrap();
      sb.group(bossGroup, wokerGroup)
          .channel(NioServerSocketChannel.class)
          // 初始化连接器
          .childHandler(new ChannelInitializer<SocketChannel>() {
            // 字符串编解码器 二次编解码 常用的有protbuf/json
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              // 通道channel设置
              ChannelPipeline pipeline = ch.pipeline();
              // 设置（字符串）解码器 和 编码器
              pipeline
                  .addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4))
                  .addLast(new LengthFieldPrepender(4))
                  .addLast(DECODER)
                  .addLast(ENCODER)
                  // 服务端连接之后的执行器，接收到消息之后的业务处理
                  .addLast(SERVER_HANDLER);
            }
          });
      ChannelFuture future = sb.bind(PORT).sync();
      // 对关闭通道进行监听
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
        bossGroup.shutdownGracefully();
        wokerGroup.shutdownGracefully();
    }
  }

  static class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
      if (!msg.isEmpty()) {
        System.out.println("接收到客户端的消息:" + msg);
      }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ctx.close();
    }
  }

}
