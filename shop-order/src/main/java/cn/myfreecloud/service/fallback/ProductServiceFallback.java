//package cn.myfreecloud.service.fallback;
//
//import cn.myfreecloud.domain.Product;
//import cn.myfreecloud.feign.ProductService;
//import org.springframework.stereotype.Service;
//
//
///**
// * 这是一个容错类,需要实现Feign所在的接口,并且实现接口中所有的方法
// * 一旦Feign远程调用出现了问题,就会进入当前类同名方法中,执行容错逻辑
// *
// */
//
//@Service
//public class ProductServiceFallback implements ProductService {
//
//    @Override
//    public Product findByPid(Integer pid) {
//        // 容错逻辑
//
//        Product product = new Product();
//
//        product.setPid(-100);
//        product.setPname("远程调用微服务出现了异常,进入了容错逻辑");
//        return product;
//    }
//}
