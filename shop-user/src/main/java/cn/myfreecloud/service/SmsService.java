package cn.myfreecloud.service;

import cn.myfreecloud.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
// 消费者组 要消费的主题
@RocketMQMessageListener(
        consumerGroup = "shop-user", // 消费者组名
        topic = "order-topic", // 消费主题
        consumeMode = ConsumeMode.ORDERLY ,// 消费模式,指定是否顺序消费 CONCURRENTLY(默认 同步消费,大家一起消费多个队列里面的消息,没有顺序) ORDERLY 顺序消费
        messageModel = MessageModel.CLUSTERING // 消费模式, 广播(每个订阅的微服务都会消费一次) 和 集群模式(一条消息 只能被一个微服务能消费)
)
public class SmsService implements RocketMQListener<Order> {

    // 消费逻辑
    @Override
    public void onMessage(Order order) {
      log.info("接收到了一个订单信息{},接下来进行订单操作",order);
    }
}
