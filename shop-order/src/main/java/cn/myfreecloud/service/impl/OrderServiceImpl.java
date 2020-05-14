package cn.myfreecloud.service.impl;


import cn.myfreecloud.dao.OrderDao;
import cn.myfreecloud.domain.Order;
import cn.myfreecloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Override
    public void createOrder(Order order) {
        orderDao.save(order);
    }
}
