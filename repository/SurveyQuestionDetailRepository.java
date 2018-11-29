package com.oee.pikachu.repository;

import com.oee.pikachu.domain.SurveyQuestionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/7.
 */
public interface SurveyQuestionDetailRepository extends JpaRepository<SurveyQuestionDetail, Long> {

    /**
     * 查询选项列表
     * @param surveyId
     * @param questionId
     * @return
     */
    List<SurveyQuestionDetail> findAllBySurveyIdAndQuestionId(Long surveyId, Long questionId);

    /**
     * 查询某调研下所有选项
     * @param surveyId
     * @return
     */
    List<SurveyQuestionDetail> findAllBySurveyId(Long surveyId);
}
