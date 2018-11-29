package com.oee.pikachu.repository;

import com.oee.pikachu.domain.SurveyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 調研倉儲服務
 * Created by Aqua on 2018/11/5.
 */
public interface SurveyRepository extends JpaRepository<SurveyInfo, Long> {

    List<SurveyInfo> findAllByStatusOrderByBeginTimeDesc(String status);
}
