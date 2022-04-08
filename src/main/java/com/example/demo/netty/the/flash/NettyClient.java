package com.example.demo.netty.the.flash;

import com.example.demo.netty.the.flash.handler.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    public static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 指定线程模型
                .group(workerGroup)
                // 指定IO类型为NIO
                .channel(NioSocketChannel.class)
                // 绑定自定义属性到channel
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                // 设置tcp底层属性
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // IO处理器
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });

        // 建立连接
        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
           if (future.isSuccess()) {
               System.out.println("连接成功!");
           } else if (retry == 0) {
               System.err.println("重试次数已用完，放弃连接!");
           } else {
               // 第几次重连
               int order = (MAX_RETRY - retry) + 1;
               // 本次重连的间隔
               int delay = 1 << order;
               System.err.println(new Date() + ":连接失败，第" + order + "次重连...");
               bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
           }
        });
    }

}
