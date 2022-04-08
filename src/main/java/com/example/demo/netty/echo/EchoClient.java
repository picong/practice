package com.example.demo.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
       this.host = host;
       this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture channelFuture = b.connect().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        String host = "10.10.10.169";
//        int port = 20001;
//        EchoClient echoClient = new EchoClient(host, port);
//        echoClient.start();
//    }



    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "017Playing_婕斿\uE694\\uD83C\\uDFBBBQB/婕斿\uE69400011-鎵撻紦.gif";
        System.out.println(str);
        System.out.println(new String(str.getBytes("unicode"), "utf-8"));
    }


}
