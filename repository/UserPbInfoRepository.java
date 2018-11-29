package com.oee.pikachu.repository;

import com.oee.pikachu.domain.UserPbInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Auth: yangyanming
 * @Date: 2018/6/9
 * @Version: 1.0
 **/
public interface UserPbInfoRepository extends JpaRepository<UserPbInfo, Long> {

    UserPbInfo findByOpenId(String openId);

    /**
     * 根据工号查询用户
     * @param jobNumber
     * @return
     */
    UserPbInfo findByJobNumber(String jobNumber);
}
