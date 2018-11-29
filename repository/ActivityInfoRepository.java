package com.oee.pikachu.repository;

import com.oee.pikachu.domain.ActivityInfo;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Aqua on 2018/11/8.
 */
public interface ActivityInfoRepository extends JpaRepository<ActivityInfo, Long> {

    ActivityInfo getById(Long id);
}
