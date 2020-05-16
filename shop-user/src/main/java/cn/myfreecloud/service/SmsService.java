package cn.myfreecloud.service;

import cn.myfreecloud.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
// 消费者组 要消费的主题
@RocketMQMessageListener(consumerGroup = "shop-user",topic = "order-topic")
public class SmsService implements RocketMQListener<Order> {

    // 消费逻辑
    @Override
    public void onMessage(Order order) {
      log.info("接收到了一个订单信息{},接下来进行订单操作",order);
    }
}
