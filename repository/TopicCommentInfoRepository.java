package com.oee.pikachu.repository;

import com.oee.pikachu.domain.TopicCommentInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aqua on 2018/11/18.
 */
public interface TopicCommentInfoRepository extends JpaRepository<TopicCommentInfo, Long> {

    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    @Query(value = "insert into topic_comment_info(topic_id, job_number, real_name, comment_text, insert_time, update_time) VALUES (?1, ?2, ?3, ?4, now(), now())", nativeQuery = true)
    void insertCommentInfo(Long topicId, String jobNumber, String realName, String commentText);
}
