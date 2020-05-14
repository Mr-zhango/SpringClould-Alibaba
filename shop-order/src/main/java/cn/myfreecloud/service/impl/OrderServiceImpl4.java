package cn.myfreecloud.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl4 {

    //
    // blockHandler 定义当资源内部发生了BlockException异常时,应该进入的方法 它捕获的是Sentinel定义的异常
    // fallback     定义当资源内部发生了 Throwable 应该进入的方法

    /**
     * 为该资源添加了两个异常处理方法
     * @param name
     * @return
     */
    // 定义资源,valie指定资源名称
    @SentinelResource(value = "message", blockHandler = "blockHandler" ,fallback = "fallback")
    public String message(String name) {
        return "message";
    }


    // 1.要求当前方法的返回值和参数要和原方法保持一致
    // 2.但是允许在参数列表的最后加入一个参数 BlockException ,用来接收原方法中发生的异常
    public String blockHandler(String name, BlockException e) {
        log.error("触发了 BlockException,内容为{}", e);
        return "BlockException";
    }

    // 接收所有的异常
    // 1.要求当前方法的返回值和参数要和原方法保持一致
    // 2.但是允许在参数列表的最后加入一个参数 BlockException ,用来接收原方法中发生的异常
    public String fallback(String name, BlockException e) {
        log.error("触发了 Throwable,内容为{}", e);
        return "Throwable";
    }
}
