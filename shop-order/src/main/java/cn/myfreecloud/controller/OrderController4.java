package cn.myfreecloud.controller;

import cn.myfreecloud.service.impl.OrderServiceImpl4;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController4 {

    @Autowired
    private OrderServiceImpl4 orderServiceImpl4;


//    // 高并发测试
//    @RequestMapping("/order/message1")
//    public String message1() throws Exception {
//        orderServiceImpl4.message();
//        return "message1";
//    }


//    // 高并发测试
//    @RequestMapping("/order/message2")
//    public String message2() throws Exception {
//        orderServiceImpl4.message();
//        return "message2";
//    }

    // 高并发测试
    @RequestMapping("/order/message3")
    public String message3() {
        return orderServiceImpl4.message("xx");
    }
}
