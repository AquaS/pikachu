package com.oee.pikachu.repository;

import com.oee.pikachu.domain.BranchIntro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Aqua on 2018/11/15.
 */
public interface BranchIntroRepository extends JpaRepository<BranchIntro, Long> {

    BranchIntro findByBranchName(String branchName);
}
