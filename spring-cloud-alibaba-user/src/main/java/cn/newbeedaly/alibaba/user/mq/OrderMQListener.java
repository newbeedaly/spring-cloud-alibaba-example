package cn.newbeedaly.alibaba.user.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "user", topic = "order-topic")
public class OrderMQListener implements RocketMQListener {

    @Override
    public void onMessage(Object o) {
        log.info("收到一个订单信息{}, 接下来发送消息: ", JSON.toJSONString(o));
    }

}
