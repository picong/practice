package com.example.demo.function.rabbit.pub1;

import com.example.demo.function.rabbit.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Recv {

    private static final String QUEUE_NAME = "fanout_exchange_queue_sms"; // 短信队列

    private static final String EXCHANGE_NAME = "test_fanout_exchange";

    public static void main(String[] args) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定队到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println(" [短信服务] received : " + msg + "!");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

}
