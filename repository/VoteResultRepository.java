package com.oee.pikachu.repository;

import com.oee.pikachu.domain.VoteResult;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aqua on 2018/11/10.
 */
public interface VoteResultRepository extends JpaRepository<VoteResult, Long> {

    VoteResult findByOpenIdAndVoteInfoIdAndVoteDetailId(String openId, Long voteInfoId, Long voteDetailId);

    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    @Query(value = "insert into vote_result(open_id, vote_info_id, vote_detail_id, insert_time, update_time) VALUES (?1, ?2, ?3, now(), now())", nativeQuery = true)
    void insertVoteResult(String openId, Long voteInfoId, Long voteDetailId);
}
