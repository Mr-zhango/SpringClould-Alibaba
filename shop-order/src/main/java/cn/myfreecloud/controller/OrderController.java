package cn.myfreecloud.controller;

import cn.myfreecloud.domain.Order;
import cn.myfreecloud.domain.Product;
import cn.myfreecloud.service.OrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;


    // 下单
    @RequestMapping("/order/product/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求", pid);

        List<ServiceInstance> instances = discoveryClient.getInstances("server-product");

        ServiceInstance serviceInstance = instances.get(0);

        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        // 调用商品微服务
        Product product = restTemplate.getForObject("http://" + host + ":" + port + "/product/" + pid, Product.class);

        log.info("商品信息查询成功,内容为{}", pid, JSON.toJSONString(product));

        Order order = new Order();

        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        // 下单
        orderService.createOrder(order);

        log.info("创建订单成功,订单内容为{}", JSON.toJSONString(order));

        return order;
    }
}
