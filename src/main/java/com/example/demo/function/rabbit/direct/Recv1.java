package com.example.demo.function.rabbit.direct;

import com.example.demo.function.rabbit.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv1 {

    public static final String EXCHANGE_NAME = "test_direct_exchange";
    public static final String QUEUE_NAME = "direct_exchange_queue_email";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 绑定队列到交换机，同时指定需要订阅的routing key。可以指定多个
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "email"); // 指定routing key为sms的消息
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println(" [邮箱服务] received : " + msg + "!");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 监听队列，手动ACK
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

}
