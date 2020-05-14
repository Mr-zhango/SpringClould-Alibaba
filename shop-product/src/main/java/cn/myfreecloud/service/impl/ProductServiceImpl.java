package cn.myfreecloud.service.impl;


import cn.myfreecloud.dao.ProductDao;
import cn.myfreecloud.domain.Product;
import cn.myfreecloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }
}
