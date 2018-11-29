package com.oee.pikachu.repository;

import com.oee.pikachu.domain.UserPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * 用戶積分倉儲服務
 * Created by Aqua on 2018/6/10.
 */
public interface UserPointsRepository extends JpaRepository<UserPoints, Long> {

//    UserPoints findUserPointsByWxid();

    List<UserPoints> findByOpenId(String openId);

    /**
     * 查询用户某年份积分
     * @param openId
     * @param date
     * @return
     */
    List<UserPoints> findByOpenIdAndDateGreaterThanEqual(String openId, Date date);
}
