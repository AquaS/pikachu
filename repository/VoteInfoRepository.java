package com.oee.pikachu.repository;

import com.oee.pikachu.domain.VoteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/10.
 */
public interface VoteInfoRepository extends JpaRepository<VoteInfo, Long> {

    List<VoteInfo> queryAllByStatus(String status);
}
