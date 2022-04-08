package com.example.demo.function.rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv {

    public static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) {

        try {
            Connection connection = ConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // 实现消费方法
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
                /**
                 * 当接收到消息后此方法将被调用
                 * @param consumerTag 消费者标签，用来标识消费者的，在监听队列时设置channel.basicConsume
                 * @param envelope 信封，通过envelope
                 * @param properties 消息属性
                 * @param body 消息内容
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // 交换机
                    String exchange = envelope.getExchange();
                    // 消息id，mq在channel中用来标识消息的id，可用于确认消息已接收
                    long deliveryTag = envelope.getDeliveryTag();
                    String msg = new String(body, "utf-8");
                    System.out.println(" [x] received: " + msg + "!");
                }
            };
            // 监听队列，第二个参数：是否自动进行消息确认。
            //参数：String queue, boolean autoAck, Consumer callback
            /**
             * 参数明细：
             * 1、queue 队列名称
             * 2、autoAck 自动回复，当消费者接收到消息后要告诉mq消息已接收，如果将此参数设置为tru表示会自动回复mq，如果设置为false要通过编程实现回复
             * 3、callback，消费方法，当消费者接收到消息要执行的方法
             */
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
