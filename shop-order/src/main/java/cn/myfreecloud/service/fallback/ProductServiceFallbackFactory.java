//package cn.myfreecloud.service.fallback;
//
//import cn.myfreecloud.domain.Product;
//import cn.myfreecloud.feign.ProductService;
//import feign.hystrix.FallbackFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//// 这是容错类,他要求我们要实现一个FallbackFactory<要为哪一个接口产生容错类>
//public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {
//
//
//    // 这就是Fegin在调用过程中产生的异常
//    @Override
//    public ProductService create(Throwable throwable) {
//
//        return new ProductService() {
//            @Override
//            public Product findByPid(Integer pid) {
//
//                log.error("{}",throwable);
//                // 容错逻辑
//                Product product = new Product();
//
//                product.setPid(-100);
//                product.setPname("远程调用微服务出现了异常,进入了容错逻辑");
//                return product;
//            }
//        };
//    }
//}
