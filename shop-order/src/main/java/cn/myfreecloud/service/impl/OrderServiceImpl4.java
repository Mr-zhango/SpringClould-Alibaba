package cn.myfreecloud.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl4 {

    // 定义资源,valie指定资源名称
   @SentinelResource("message")
    public String message() {
        return "message";
    }
}
