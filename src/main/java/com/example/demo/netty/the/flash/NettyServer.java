package com.example.demo.netty.the.flash;

import com.example.demo.netty.the.flash.handler.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyServer {

    private static final int BEGIN_PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        final ServerBootstrap b = new ServerBootstrap();
        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                .childAttr(clientKey, "clientValue")
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println(ch.attr(clientKey).get());
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });
        bind(b, BEGIN_PORT);
    }

    private static void bind(final ServerBootstrap b, int beginPort) {
        b.bind(beginPort).addListener(future -> {
           if (future.isSuccess()) {
               System.out.println("端口[" + beginPort + "]绑定成功!");
           } else {
               System.out.println("端口[" + beginPort + "]绑定失败!");
               bind(b,beginPort + 1);
           }
        });
    }

}
