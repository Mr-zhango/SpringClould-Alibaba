package cn.myfreecloud.controller;

import cn.myfreecloud.domain.Order;
import cn.myfreecloud.domain.Product;
import cn.myfreecloud.feign.ProductService;
import cn.myfreecloud.service.OrderService;
import cn.myfreecloud.service.impl.OrderServiceImpl5;
import cn.myfreecloud.service.impl.OrderServiceImpl6;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController6 {

    @Autowired
    private OrderServiceImpl6 orderService;

    // 下单
    @RequestMapping("/order/product/{pid}")
    public Order order(@PathVariable("pid") Integer pid) throws Exception {

        return  orderService.createOrder(pid);
    }

}
