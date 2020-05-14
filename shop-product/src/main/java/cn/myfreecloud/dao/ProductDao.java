package cn.myfreecloud.dao;

import cn.myfreecloud.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {

}
