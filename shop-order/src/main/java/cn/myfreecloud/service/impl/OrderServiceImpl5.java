package cn.myfreecloud.service.impl;


import cn.myfreecloud.dao.OrderDao;
import cn.myfreecloud.dao.TxLogDao;
import cn.myfreecloud.domain.Order;
import cn.myfreecloud.domain.TxLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl5 {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TxLogDao txLogDao;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;



    // 半事务提交
    public void createOrderHalfTranscation(Order order) {

        // 半事务id
        String txId = UUID.randomUUID().toString();

        // 发送半事务消息
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_group",
                "tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId", txId).build(),
                order
        );
    }


    @Transactional
    public void createOrder(String txId, Order order) {

        // 本地事务
        //保存订单
        orderDao.save(order);

        TxLog txLog = new TxLog();
        txLog.setTxId(txId);
        txLog.setDate(new Date());

        //记录mq事物日志
        txLogDao.save(txLog);
    }
}
