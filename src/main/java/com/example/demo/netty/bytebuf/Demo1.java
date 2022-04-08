package com.example.demo.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
//        extracted();
//        extracted1();
//        extracted2();

        long startTime = System.nanoTime();
        Thread t = new Thread(() -> {
            try {
                TimeUnit.DAYS.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Daemon-T");
        t.setDaemon(true);
        t.start();
        TimeUnit.SECONDS.sleep(15);
        System.out.println(" 系统退出， 程序执行" + (System.nanoTime() - startTime) / 1000 / 1000 /1000 + "s");
    }

    /**
     * getByte setByte方法不会改变readerIndex和writerIndex
     */
    private static void extracted2() {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", Charset.forName("UTF-8"));
        System.out.println((char)buf.getByte(0));

        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.setByte(0, 'B');
        System.out.println((char) buf.getByte(0));
        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();
        System.out.println(buf.toString(Charset.forName("UTF-8")));
        System.out.println(ByteBufUtil.hexDump(buf));
    }

    /**
     * 如果需要已有的缓冲区的全新副本，使用 copy() 或者 copy(int, int)
     */
    private static void extracted1() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf copy = buf.copy(0, 14);
        System.out.println(copy.toString(utf8));

        buf.setByte(0, 'J');
        assert buf.getByte(0) != copy.getByte(0);
    }

    /**
     * slice duplicate 获取byteBuf的视图，共享底层数据
     */
    private static void extracted() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);

        ByteBuf sliced = buf.slice(0, 14);
//        System.out.println(sliced.toString(utf8));
        buf.setByte(0, 'J');
        System.out.println(sliced.toString(utf8));
        assert buf.getByte(0) == sliced.getByte(0);
    }

}
