package cn.newbeedaly.alibaba.order.message;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * RocketMQ 消息发送
 */
public class RocketMQSendTest {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 1.创建消息生产者，指定生产者所属的组名
        DefaultMQProducer producer = new DefaultMQProducer("newbeedaly-group");
        // 2.指定Nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 3.启动生产者
        producer.start();
        // 4.创建消息对像，指定主题、标签和消息体
        Message msg = new Message("newbeedalyTopic", "newbeedalyTag", "hello RocketMQ".getBytes(StandardCharsets.UTF_8));
        // 5.发送消息
        producer.send(msg);
        // 6.关闭生产者
        producer.shutdown();
    }
}

