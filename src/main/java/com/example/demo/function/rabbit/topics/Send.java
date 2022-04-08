package com.example.demo.function.rabbit.topics;

import com.example.demo.function.rabbit.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

/**
 * topic 类型exchange
 */
public class Send {

    public static final String EXCHANGE_NAME = "test_topic_exchange";

    public static void main(String[] args) throws Exception{

        try (// 获取到连接
             Connection connection = ConnectionUtil.getConnection();
             // 获取通道
             Channel channel = connection.createChannel()) {
            // 声明exchange，指定为topic类型
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true);

            // 消息内容
            String message = "这是一只行动迅速的橙色的兔子";
            // 发送消息，并且指定 routing key为：quick.orange.rabbit
            channel.basicPublish(EXCHANGE_NAME, "quick.orange.rabbit", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [动物描述：] Sent '" + message + "'");
        }
    }


}
