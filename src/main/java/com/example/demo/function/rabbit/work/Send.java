package com.example.demo.function.rabbit.work;

import com.example.demo.function.rabbit.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Send {

    public static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) {

        try (Connection connection = ConnectionUtil.getConnection();
             Channel channel = connection.createChannel()) {
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 循环发布任务
            Stream.iterate(0, x -> x + 1).limit(50).forEach(i -> {
                // 消息内容
                String message = "task .. " + i;
                try {
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                    System.out.println(" [x] Sent '" + message + "'");
                    TimeUnit.MILLISECONDS.sleep(i * 2);
                } catch (Exception e) {
                    return;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
