package cn.myfreecloud.service.impl;

import cn.myfreecloud.dao.TxLogDao;
import cn.myfreecloud.domain.Order;
import cn.myfreecloud.domain.TxLog;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


@Service
@RocketMQTransactionListener(txProducerGroup = "tx_producer_group")
public class OrderServiceImpl5Listener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderServiceImpl5 orderServiceImpl5;

    @Autowired
    private TxLogDao txLogDao;

    //执行本地事物
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        String txId = (String) msg.getHeaders().get("txId");

        try {
            //本地事物
            Order order = (Order) arg;

            orderServiceImpl5.createOrder(txId,order);

            // 提交事务
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            // 回滚事务
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    //mq 进行消息回查方法(mq等待时间过长没有收到回复或者是服务宕机的时候使用)
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {

        // 查询本地事务状态
        String txId = (String) msg.getHeaders().get("txId");
        TxLog txLog = txLogDao.findById(txId).get();

        if (txLog != null){
            //本地事物(订单)成功了
            return RocketMQLocalTransactionState.COMMIT;
        }else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
