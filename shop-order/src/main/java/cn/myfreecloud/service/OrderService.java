package cn.myfreecloud.service;

import cn.myfreecloud.domain.Order;

public interface OrderService {
    // 创建订单
    void createOrder(Order order);
}
