package com.example.demo.function.rabbit.direct;

import com.example.demo.function.rabbit.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

/**
 * 直连模式，默认就是direct 类型的exchange
 */
public class Send {

    public static final String EXCHANGE_NAME = "test_direct_exchange";

    public static void main(String[] args) throws Exception {
        try (Connection connection = ConnectionUtil.getConnection();
             Channel channel = connection.createChannel()) {
            // 声明exchange, 指定类型为 direct
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            // 消息内容
            String message = "注册成功！请短信回复[T]退订";
            // 发送消息，并且routing key为：sms，只有短信服务能接收到消息
            channel.basicPublish(EXCHANGE_NAME, "sms", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }


}
