package cn.myfreecloud.service.impl;


import cn.myfreecloud.dao.ProductDao;
import cn.myfreecloud.domain.Product;
import cn.myfreecloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    public void productService(Integer pid, Integer number) {
        Product product = productDao.findById(pid).get();

        // 省略库存检查
        // 内存中进行扣除
        product.setStock(product.getStock() - number);

        // 模拟异常
        int i = 1 / 0;
        // 持久化数据库
        productDao.save(product);
    }
}
