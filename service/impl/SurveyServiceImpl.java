package com.oee.pikachu.service.impl;

import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.domain.*;
import com.oee.pikachu.domain.VO.SurveyQuestion;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.repository.*;
import com.oee.pikachu.service.SurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 調研服務實現類
 * Created by Aqua on 2018/11/5.
 */
@Slf4j
@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyQuestionInfoRepository sqiRepository;

    @Autowired
    private SurveyQuestionDetailRepository sqdRepository;

    @Autowired
    private VoteInfoRepository voteInfoRepository;

    @Autowired
    private VoteDetailRepository voteDetailRepository;

    @Autowired
    private VoteResultRepository voteResultRepository;

    @Autowired
    private SurveyResultRepository resultRepository;

    @Override
    public List<SurveyInfo> querySurveyInfoList() {
        List<SurveyInfo> infoList = surveyRepository.findAllByStatusOrderByBeginTimeDesc("0");
        if (CollectionUtils.isEmpty(infoList)) {
            log.info("there is no available survey for now");
            return Collections.emptyList();
        }
        return infoList;
    }

    @Override
    public List<SurveyQuestion> querySurveyQuestions(SurveyInfo surveyInfo) {
        if (surveyInfo.getId() == null) {
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }
        List<SurveyQuestionInfo> infoList = sqiRepository.findAllBySurveyId(surveyInfo.getId());
        if (CollectionUtils.isEmpty(infoList)) {
            log.info("there is no questions in this survey, surveyId:{}", surveyInfo.getId());
            throw new ErrorCodeException(ExceptionMsg.QuestionNotExist);
        }
        List<SurveyQuestion> resultList = infoList.stream().map(info -> SurveyQuestion.fromQuestionInfo(info))
                .collect(Collectors.toList());
        List<SurveyQuestionDetail> details = sqdRepository.findAllBySurveyId(surveyInfo.getId());
        Map<Long, List<SurveyQuestionDetail>> questionMap = details.stream()
                .collect(Collectors.groupingBy(SurveyQuestionDetail::getQuestionId));
        resultList.stream().forEach(info -> {
            info.setQuestionDetails(questionMap.get(info.getId()));
        });
        return resultList;
    }

    @Override
    public Response submitAnswers(List<UserSurveyAnswer> answers) {

        return null;
    }

    @Override
    public ResponseData queryVoteInfoList() {

        List<VoteInfo> voteInfoList = voteInfoRepository.queryAllByStatus("0");
        if (CollectionUtils.isEmpty(voteInfoList)) {
            log.info("vote info list is null ");
            throw new ErrorCodeException(ExceptionMsg.VoteNotExist);
        }
        return new ResponseData(ExceptionMsg.SUCCESS, voteInfoList);
    }

    @Override
    public ResponseData queryVoteDetailByVoteInfo(Long voteId) {
        if (voteId == null) {
            log.info("param is not correct");
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }
        List<VoteDetail> voteDetails = voteDetailRepository.findAllByVoteId(voteId);
        if (CollectionUtils.isEmpty(voteDetails)) {
            log.info("no vote detail exist in this vote:{}", voteId);
            throw new ErrorCodeException(ExceptionMsg.VoteDetailNotExist);
        }

        return new ResponseData(ExceptionMsg.SUCCESS, voteDetails);
    }

    @Override
    public Response vote(String openId, Long voteInfoId, Long voteDetailId) {
        if (voteInfoId == null || voteDetailId == null || openId == null) {
            log.info("param is not correct");
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }
        VoteResult voteResult = voteResultRepository.findByOpenIdAndVoteInfoIdAndVoteDetailId(openId, voteInfoId, voteDetailId);
        if (voteResult != null) {
            log.info("user:{} has voted", openId);
            throw new ErrorCodeException(ExceptionMsg.UserHasVoted);
        }
        try {
            voteResultRepository.insertVoteResult(openId, voteInfoId, voteDetailId);
            return new Response(ExceptionMsg.SUCCESS);
        } catch (Exception e) {
            log.info("insert vote_result failed, due to error:", e);
            throw new ErrorCodeException(ExceptionMsg.DataBaseError);
        }
    }

    @Override
    public Response submitSurvey(String openId, Long surveyId, Map<Long, String> answers) {
        if (CollectionUtils.isEmpty(answers)) {
            log.info("user:{} have no choice", openId);
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }

        // 任取一个questionId
        Long questionId = new ArrayList<>(answers.keySet()).get(0);
        SurveyResult surveyResult = resultRepository.findByOpenIdAndSurveyIdAndQuestionId(openId, surveyId, questionId);
        if (surveyResult != null) {
            log.info("user:{} has submitted survey:{}", openId, surveyId);
            throw new ErrorCodeException(ExceptionMsg.UserHasSurveyed);
        }

        List<SurveyResult> results = new ArrayList<>();
        answers.entrySet().forEach(entry -> {
            SurveyResult result = new SurveyResult();
            result.setOpenId(openId).setSurveyId(surveyId).setQuestionId(entry.getKey()).setMyOption(entry.getValue());
            results.add(result);
        });
        try {
            resultRepository.saveAll(results);
            return new Response(ExceptionMsg.SUCCESS);
        } catch (Exception e) {
            log.info("batch insert survey_result failed, due to error:", e);
            throw new ErrorCodeException(ExceptionMsg.DataBaseError);
        }
    }
}
