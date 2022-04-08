package com.example.demo.function.rabbit.pub1;

import com.example.demo.function.rabbit.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

public class Send {

    public static final String EXCHANGE_NAME = "test_fanout_exchange";

    public static void main(String[] args) throws Exception {

        try (Connection connection = ConnectionUtil.getConnection();
            Channel channel = connection.createChannel()) {
            // 声明exchange, 指定类型为fanout
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            // 消息内容
            String message = "注册成功！！";
            // 发布消息到Exchange
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [生产者] Sent '" + message + "'");
        }
    }


}
