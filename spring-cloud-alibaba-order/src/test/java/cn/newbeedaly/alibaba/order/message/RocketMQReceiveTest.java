package cn.newbeedaly.alibaba.order.message;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

public class RocketMQReceiveTest {

    public static void main(String[] args) throws MQClientException {
        // 1.创建消息消费者，指定消费者所属的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("newbeedaly-group");
        // 2.指定Nameserver地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
        // 3.指定消费者订阅的主题和标签
        consumer.subscribe("newbeedalyTopic", "*");
        // 4.设置回调函数，编写处理消息的方法
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            // 返回消费状态
            System.out.println("Receive New Messages:" + JSON.toJSONString(list));
            list.forEach(e-> {
                System.out.println("Receive New Messages Body:" + new String(e.getBody()));

            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // 5.启动消息消费者
        consumer.start();
    }
}

