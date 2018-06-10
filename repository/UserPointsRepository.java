package com.oee.pikachu.repository;

import com.oee.pikachu.domain.UserPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Aqua on 2018/6/10.
 */
public interface UserPointsRepository extends JpaRepository<UserPoints, Long> {

//    UserPoints findUserPointsByWxid();

    List<UserPoints> findByWxid(String wxid);

    /**
     * 查询用户某年份积分
     * @param wxid
     * @param date
     * @return
     */
    List<UserPoints> findByWxidAndDateGreaterThanEqual(String wxid, Date date);
}
