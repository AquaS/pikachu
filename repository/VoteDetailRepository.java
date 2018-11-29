package com.oee.pikachu.repository;

import com.oee.pikachu.domain.VoteDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/10.
 */
public interface VoteDetailRepository extends JpaRepository<VoteDetail, Long> {

    List<VoteDetail> findAllByVoteId(Long voteId);
}
