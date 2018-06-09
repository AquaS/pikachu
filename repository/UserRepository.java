package com.oee.pikachu.repository;

import com.oee.pikachu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Auth: yangyanming
 * @Date: 2018/6/9
 * @Version: 1.0
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    User findByWxid(String wxid);
}
