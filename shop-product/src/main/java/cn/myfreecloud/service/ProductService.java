package cn.myfreecloud.service;

import cn.myfreecloud.domain.Product;

public interface ProductService {
    // 根据id查询商品信息
    Product findByPid(Integer pid);
}
