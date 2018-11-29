package com.oee.pikachu.repository;

import com.oee.pikachu.domain.TopicLikeInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aqua on 2018/11/29.
 */
public interface TopicLikeInfoRepository extends JpaRepository<TopicLikeInfo, Long> {

    TopicLikeInfo getByTopicIdAndJobNumber(Long topicId, String jobNumber);

    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    @Query(value = "insert into topic_like_info(topic_id, job_number, real_name, insert_time, update_time) VALUES (?1, ?2, ?3, now(), now())", nativeQuery = true)
    void insertLikeInfo(Long topicId, String jobNumber, String realName);
}
