package com.oee.pikachu.repository;

import com.oee.pikachu.domain.ActivityDetail;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aqua on 2018/11/8.
 */
public interface ActivityDetailRepository extends JpaRepository<ActivityDetail, Long> {

    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    @Query(value = "insert into activity_detail(activity_id, job_number, real_name, insert_time, update_time) VALUES (?1, ?2, ?3, now(), now())", nativeQuery = true)
    void insertActivityDetail(Long activityId, String jobNumber, String realName);

    ActivityDetail getByActivityIdAndJobNumber(Long activityId, String jobNumber);
}
