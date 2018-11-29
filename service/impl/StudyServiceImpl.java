package com.oee.pikachu.service.impl;

import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.comm.enums.StudyExamStatus;
import com.oee.pikachu.domain.*;
import com.oee.pikachu.domain.VO.ExamRequestVO;
import com.oee.pikachu.domain.VO.StudyClassInfoResponseVO;
import com.oee.pikachu.domain.VO.StudyExamQuestionVO;
import com.oee.pikachu.domain.VO.StudyExamResponseVO;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.repository.*;
import com.oee.pikachu.service.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Aqua on 2018/11/7.
 */
@Slf4j
@Service
public class StudyServiceImpl implements StudyService {

    @Autowired
    private StudyModuleInfoRepository moduleInfoRepository;

    @Autowired
    private StudyClassInfoRepository classInfoRepository;

    @Autowired
    private UserPbInfoRepository userPbInfoRepository;

    @Autowired
    private StudyExamDetailRepository examDetailRepository;

    @Autowired
    private StudyExamInfoRepository examInfoRepository;

    @Autowired
    private StudyExamQuestionInfoRepository questionInfoRepository;

    @Autowired
    private StudyExamQuestionDetailRepository questionDetailRepository;

    @Override
    public ResponseData queryStudyModule() {
        List<StudyModuleInfo> moduleInfos = moduleInfoRepository.findAll();
        if (CollectionUtils.isEmpty(moduleInfos)) {
            log.info("query study_class_info is null");
            throw new ErrorCodeException(ExceptionMsg.StudyModuleIsNull);
        }
        Map<Long, String> moduleMap = moduleInfos.stream()
                .collect(Collectors.toMap(module -> module.getId(), module -> module.getStudyModule()));
        List<StudyClassInfo> classInfos = classInfoRepository.findAll();
        if (CollectionUtils.isEmpty(classInfos)) {
            log.info("query study_class_info is null");
            throw new ErrorCodeException(ExceptionMsg.StudyModuleIsNull);
        }
        Map<Long, List<StudyClassInfo>> classInfoMap = classInfos.stream()
                .collect(Collectors.groupingBy(StudyClassInfo::getStudyModuleId));
        List<StudyClassInfoResponseVO> response = new ArrayList<>();
        moduleMap.entrySet().stream().forEach(module ->{
            StudyClassInfoResponseVO responseVO = new StudyClassInfoResponseVO();
            responseVO.setStudyModuleId(module.getKey()).setStudyModule(module.getValue());
            responseVO.setClassInfos(classInfoMap.get(module.getKey()));
            response.add(responseVO);
        });
        return new ResponseData(ExceptionMsg.SUCCESS, response);
    }

    @Override
    public ResponseData queryStudyExam(String openId, String studyModule, String studyClass) {
        if (openId == null || studyModule == null || studyClass == null) {
            log.error("queryStudyExam param error");
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }
        UserPbInfo userPbInfo = userPbInfoRepository.findByOpenId(openId);
        if (userPbInfo == null) {
            log.info("user :{} is not exist", openId);
            throw new ErrorCodeException(ExceptionMsg.BindNotExist);
        }
        List<StudyExamInfo> examInfos = examInfoRepository.findAllByModuleOneAndModuleTwo(studyModule, studyClass);
        if (CollectionUtils.isEmpty(examInfos)) {
            log.info("no exam in moduleOne:{} moduleTwo:{}", studyModule, studyClass);
            throw new ErrorCodeException(ExceptionMsg.StudyNotExist);
        }
        List<StudyExamResponseVO> resultList = new ArrayList<>();
        examInfos.stream().forEach(examInfo -> {
            StudyExamResponseVO responseVO = new StudyExamResponseVO();
            responseVO.setBeginTime(examInfo.getBeginTime()).setStudyExamName(examInfo.getStudyExamName());
            List<StudyExamDetail> examDetails = examDetailRepository.findAllByExamIdAndJobNumber(examInfo.getId(),
                    userPbInfo.getJobNumber());
            if (CollectionUtils.isEmpty(examDetails)) {
                responseVO.setStatus(StudyExamStatus.NOT_ANSWER.getStatusDesc());
            } else {
                Integer score = examDetails.stream().map(StudyExamDetail::getMyGrade).reduce(0, Integer::sum);
                responseVO.setStatus(StudyExamStatus.ANSWERED.getStatusDesc()).setGrade(score);
            }
            resultList.add(responseVO);
        });


        return new ResponseData(ExceptionMsg.SUCCESS, resultList);
    }

    @Override
    public ResponseData queryExamQuestions(Long examId) {
        List<StudyExamQuestionInfo> questions = questionInfoRepository.findAllByExamId(examId);
        if (CollectionUtils.isEmpty(questions)) {
            log.info("no question in exam :{}", examId);
            throw new ErrorCodeException(ExceptionMsg.NoQuestionInExam);
        }
        List<Long> questionIds = questions.stream().map(StudyExamQuestionInfo::getId).collect(Collectors.toList());
        List<StudyExamQuestionDetail> questionDetails = questionDetailRepository.findAllByQuestionIdIn(questionIds);
        if (CollectionUtils.isEmpty(questionDetails)) {
            log.info("no question option in exam :{}", examId);
            throw new ErrorCodeException(ExceptionMsg.NoQuestionInExam);
        }
        Map<Long, List<StudyExamQuestionDetail>> optionMap = questionDetails.stream()
                .collect(Collectors.groupingBy(StudyExamQuestionDetail::getQuestionId));

        List<StudyExamQuestionVO> questionList = new ArrayList<>();
        questions.stream().forEach(question -> {
            StudyExamQuestionVO questionVO = new StudyExamQuestionVO();
            questionVO.setQuestionName(question.getQuestionName()).setQuestionType(question.getQuestionType())
                    .setOptions(optionMap.get(question.getId())).setQuestionId(question.getId());
            questionList.add(questionVO);
        });
        return new ResponseData(ExceptionMsg.SUCCESS, questionList);
    }

    @Transactional
    @Override
    public Response submitExamAnswer(String openId, ExamRequestVO requestVO) {
        if (CollectionUtils.isEmpty(requestVO.getAnswers())) {
            log.info("user:{} have no choice", openId);
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }
        UserPbInfo userPbInfo = userPbInfoRepository.findByOpenId(openId);
        if (userPbInfo == null) {
            log.info("user :{} is not exist", openId);
            throw new ErrorCodeException(ExceptionMsg.BindNotExist);
        }
        List<StudyExamDetail> examDetails = examDetailRepository.findAllByExamIdAndJobNumber(requestVO.getExamId(),
                userPbInfo.getJobNumber());
        if (!CollectionUtils.isEmpty(examDetails)) {
            log.info("user:{} has submitted exam:{}", openId, requestVO.getExamId());
            throw new ErrorCodeException(ExceptionMsg.UserHasSurveyed);
        }
        StudyExamInfo examInfo = examInfoRepository.getById(requestVO.getExamId());
        List<StudyExamQuestionInfo> questionInfos = questionInfoRepository.findAllByExamId(requestVO.getExamId());
        Map<Long, List<StudyExamQuestionInfo>> questionMap = questionInfos.stream()
                .collect(Collectors.groupingBy(StudyExamQuestionInfo::getId));
        List<StudyExamDetail> answerList = new ArrayList<>();
        requestVO.getAnswers().entrySet().forEach(entry -> {
            StudyExamDetail examDetail = new StudyExamDetail();
            examDetail.setExamId(requestVO.getExamId()).setJobNumber(userPbInfo.getJobNumber())
                    .setMyOption(entry.getValue()).setRealName(userPbInfo.getRealName()).setQuestionId(entry.getKey())
                    .setMyRight(0).setQuestionNo(0);
            if (entry.getValue().equalsIgnoreCase(questionMap.get(entry.getKey()).get(0).getRightAnswers())) {
                examDetail.setMyGrade(examInfo.getOneMark());
            } else {
                examDetail.setMyGrade(0);
            }
            answerList.add(examDetail);
        });
        try {
            examDetailRepository.saveAll(answerList);
            return new Response(ExceptionMsg.SUCCESS);
        } catch (Exception e) {
            log.info("batch insert exam answer failed, due to error:", e);
            throw new ErrorCodeException(ExceptionMsg.DataBaseError);
        }
    }
}
