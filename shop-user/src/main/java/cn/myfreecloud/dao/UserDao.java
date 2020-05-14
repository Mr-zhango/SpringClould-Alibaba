package cn.myfreecloud.dao;

import cn.myfreecloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

}
