package com.oee.pikachu.service;

import com.oee.pikachu.domain.VO.ExamRequestVO;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;

/**
 * Created by Aqua on 2018/11/27.
 */
public interface StudyService {

    /**
     * 查询考试模块
     *
     * @return
     */
    ResponseData queryStudyModule();

    /**
     * 查询考试列表
     *
     * @param openId
     * @param studyModule
     * @param studyClass
     * @return
     */
    ResponseData queryStudyExam(String openId, String studyModule, String studyClass);

    /**
     * 查询考试题目
     *
     * @param examId
     * @return
     */
    ResponseData queryExamQuestions(Long examId);

    /**
     * 考试提交答案
     *
     * @param openId
     * @param requestVO
     * @return
     */
    Response submitExamAnswer(String openId, ExamRequestVO requestVO);
}
