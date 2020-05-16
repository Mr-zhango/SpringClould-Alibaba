package cn.myfreecloud.service;

import cn.myfreecloud.dao.UserDao;
import cn.myfreecloud.domain.Order;
import cn.myfreecloud.domain.User;
import cn.myfreecloud.utils.SmsUtil;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service("shopSmsService")
// 消费者组 要消费的主题
@RocketMQMessageListener(
        consumerGroup = "shop-user", // 消费者组名
        topic = "order-topic", // 消费主题
        consumeMode = ConsumeMode.ORDERLY,// 消费模式,指定是否顺序消费 CONCURRENTLY(默认 同步消费,大家一起消费多个队列里面的消息,没有顺序) ORDERLY 顺序消费
        messageModel = MessageModel.CLUSTERING // 消费模式, 广播(每个订阅的微服务都会消费一次) 和 集群模式(一条消息 只能被一个微服务能消费)
)
public class SmsService implements RocketMQListener<Order> {

    @Autowired
    private UserDao userDao;

    // 消费逻辑
    @Override
    public void onMessage(Order order) {
        log.info("接收到了一个订单信息{},接下来就可以发送短信通知了", order);

        //根据uid 获取手机号
        User user = userDao.findById(order.getUid()).get();

        //生成验证码 1-9 6
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(new Random().nextInt(9) + 1);
        }
        String smsCode = builder.toString();

        Param param = new Param(smsCode);

        try {
            //发送短信 {"code":"123456"}
            SmsUtil.sendSms(user.getTelephone(), "自由云网络", "SMS_137815289", JSON.toJSONString(param));
            log.info("短信发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Param {
        private String code;
    }
}
