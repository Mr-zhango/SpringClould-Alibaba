package cn.myfreecloud.feign;

import cn.myfreecloud.domain.Product;
import cn.myfreecloud.service.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "server-product",
        //fallback = ProductServiceFallback.class,
        fallbackFactory = ProductServiceFallbackFactory.class
) //value用户指定调用nacos下的那个微服务
public interface ProductService  {

    // 调用商品微服务
    // Product product = restTemplate.getForObject("http://server-product/product/" + pid, Product.class);

    //@FeignClient 的value值 + @RequestMapping value值,就是一个完整的请求地址 "http://server-product/product/" + pid,
    @RequestMapping("/product/{pid}") // 指定请求的uri部分
    Product findByPid(@PathVariable Integer pid);
}
