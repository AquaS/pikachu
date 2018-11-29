package com.oee.pikachu.repository;


import com.oee.pikachu.domain.BranchManage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Aqua on 2018/11/10.
 */
public interface BranchManageRepository extends JpaRepository<BranchManage, Long> {

    BranchManage findBranchManageByBranchName(String branchName);
}
