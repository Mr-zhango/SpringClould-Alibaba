package cn.myfreecloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController3 {


    // 高并发测试
    @RequestMapping("/order/sentinel")
    public String sentinel() throws Exception {
        return "sentinel";
    }


    // 高并发测试
    @RequestMapping("/order/sentinel2")
    public String sentinel2() throws Exception {
        return "sentinel2";
    }
}
