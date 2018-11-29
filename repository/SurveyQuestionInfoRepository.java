package com.oee.pikachu.repository;

import com.oee.pikachu.domain.SurveyQuestionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/7.
 */
public interface SurveyQuestionInfoRepository extends JpaRepository<SurveyQuestionInfo, Long> {

    /**
     * 根据调研ID查询问题列表
     * @param surveyId
     * @return
     */
    List<SurveyQuestionInfo> findAllBySurveyId(Long surveyId);
}
