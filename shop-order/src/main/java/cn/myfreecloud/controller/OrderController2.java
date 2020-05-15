package cn.myfreecloud.controller;

import cn.myfreecloud.domain.Order;
import cn.myfreecloud.domain.Product;
import cn.myfreecloud.feign.ProductService;
import cn.myfreecloud.service.OrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController2 {


    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;


    // 下单
    @RequestMapping("/order/product/{pid}")
    public Order order(@PathVariable("pid") Integer pid) throws Exception {
        log.info("接收到{}号商品的下单请求", pid);

       // 模拟调用商品微服务需要2s时间
        Thread.sleep(2000L);

        // 使用feign的形式调用
        Product product = productService.findByPid(pid);

        if(product.getPid() == -100){
            Order order = new Order();
            order.setOid(-100L);
            order.setPname("下单失败");
            return order;
        }

        log.info("商品信息查询成功,内容为{}", pid, JSON.toJSONString(product));

        Order order = new Order();

        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        // 防止下单出现垃圾数据
        //orderService.createOrder(order);

        //log.info("创建订单成功,订单内容为{}", JSON.toJSONString(order));

        return order;
    }

    // 高并发测试
    @RequestMapping("/order/meaasge")
    public String message() throws Exception {
        return "高并发测试";
    }
}
