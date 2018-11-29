package com.oee.pikachu.repository;

import com.oee.pikachu.domain.BranchUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/10.
 */
public interface BranchUserRepository extends JpaRepository<BranchUser, Long> {

    List<BranchUser> queryAllByBranchId(Long branchId);
}
