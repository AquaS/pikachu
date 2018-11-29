package com.oee.pikachu.service;

import com.oee.pikachu.domain.SurveyInfo;
import com.oee.pikachu.domain.VO.SurveyQuestion;
import com.oee.pikachu.domain.UserSurveyAnswer;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;

import java.util.List;
import java.util.Map;

/**
 * 調研服務
 * Created by Aqua on 2018/11/5.
 */
public interface SurveyService {

    /**
     * 獲取調研列表
     *
     * @return
     */
    List<SurveyInfo> querySurveyInfoList();

    /**
     * 獲取調研問題列表
     *
     * @param surveyInfo
     * @return
     */
    List<SurveyQuestion> querySurveyQuestions(SurveyInfo surveyInfo);

    /**
     * 提交答案
     *
     * @param answers
     * @return
     */
    Response submitAnswers(List<UserSurveyAnswer> answers);

    /**
     * 查询投票列表
     *
     * @return
     */
    ResponseData queryVoteInfoList();

    /**
     * 查询投票对象
     *
     * @param voteId
     * @return
     */
    ResponseData  queryVoteDetailByVoteInfo(Long voteId);

    /**
     * 用户投票
     *
     * @param openId
     * @param voteInfoId
     * @param voteDetailId
     * @return
     */
    Response vote(String openId, Long voteInfoId, Long voteDetailId);

    /**
     * 用户提交调研答案
     *
     * @param openId
     * @param surveyId
     * @param answers
     * @return
     */
    Response submitSurvey(String openId, Long surveyId, Map<Long, String> answers);
}
