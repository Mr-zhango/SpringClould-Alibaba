package cn.myfreecloud.controller;

import cn.myfreecloud.domain.Order;
import cn.myfreecloud.domain.Product;
import cn.myfreecloud.feign.ProductService;
import cn.myfreecloud.service.OrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Slf4j
//@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductService productService;


    // 下单
    @RequestMapping("/order/product/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求", pid);

        // List<ServiceInstance> instances = discoveryClient.getInstances("server-product");

        // 使用ribbon实现客户端负载均衡
        // 调用商品微服务
        // Product product = restTemplate.getForObject("http://server-product/product/" + pid, Product.class);

        // 使用feign的形式调用
        Product product = productService.findByPid(pid);

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
