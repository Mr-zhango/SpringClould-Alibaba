package cn.myfreecloud.dao;

import cn.myfreecloud.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {

}
