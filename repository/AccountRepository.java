package com.oee.pikachu.repository;

import com.oee.pikachu.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Aqua on 2018/10/28.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * 根据用户名查询登陆账户
     * @param workNum
     * @return
     */
    Account findByWorkNum(String workNum);
}
