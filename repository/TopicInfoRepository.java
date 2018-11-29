package com.oee.pikachu.repository;

import com.oee.pikachu.domain.TopicInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by Aqua on 2018/11/18.
 */
public interface TopicInfoRepository extends JpaRepository<TopicInfo, Long> {

    List<TopicInfo> findAllByStatusOrderByReleaseTimeDesc(String status);

    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    @Query(value = "update topic_info set comment_no = comment_no + 1 where id = ?1", nativeQuery = true)
    void updateTopicInfoForUpdate(Long id);

    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    @Query(value = "insert into topic_info(topic_type, topic_name, job_number, real_name, release_time, like_no, comment_no, status, topic_text, insert_time, update_time) VALUES (?1, ?2, ?3, ?4, now(), 0, 0, '', ?5, now(), now())", nativeQuery = true)
    void insertTopicInfo(String topicType, String topicName, String jobNumber, String realName, String topicText);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select * from topic_info t where t.id = ?1", nativeQuery = true)
    TopicInfo getTopicForUpdate(Long id);
}
