package com.oee.pikachu.webapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.comm.utils.CookieUtils;
import com.oee.pikachu.domain.VO.ExamRequestVO;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Aqua on 2018/11/8.
 */
@RestController
@RequestMapping("/study")
public class StudyController extends BaseController {

    @Autowired
    private StudyService studyService;

    @ResponseBody
    @RequestMapping(value = "/getStudyModule")
    @LoggerManage(description = "获取学习模块")
    public ResponseData queryStudyModule(HttpServletRequest request, HttpServletResponse response) {

        return studyService.queryStudyModule();
    }

    @ResponseBody
    @RequestMapping(value = "/getStudyExam")
    @LoggerManage(description = "获取学习模块考试列表")
    public ResponseData queryStudyExam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtils.readAll(reader);
        JSONObject questJson = JSON.parseObject(body);
        logger.info("request is:{}", JSON.toJSONString(questJson));
        String studyModule = questJson.getString("studyModule");
        String studyClass = questJson.getString("studyClass");

        if (studyModule == null || studyClass == null) {
            logger.error("getStudyExam request param error");
            return new ResponseData(ExceptionMsg.ParamError);
        }
        String openId = CookieUtils.getOpenidFromCookie(request);
        return studyService.queryStudyExam(openId, studyModule, studyClass);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamQuestion")
    @LoggerManage(description = "获取学习模块考试问题列表")
    public ResponseData queryExamQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtils.readAll(reader);
        JSONObject questJson = JSON.parseObject(body);
        logger.info("request is:{}", JSON.toJSONString(questJson));
        Long examId = questJson.getLongValue("examId");
        if (examId == null) {
            logger.error("queryExamQuestion request param error");
            return new ResponseData(ExceptionMsg.ParamError);
        }
        return studyService.queryExamQuestions(examId);
    }

    @ResponseBody
    @RequestMapping(value = "/submitExamAnswer")
    @LoggerManage(description = "提交考试答案")
    public Response submitExamAnswer(@RequestBody ExamRequestVO requestVO, HttpServletRequest request) {
        return studyService.submitExamAnswer("wx123", requestVO);
    }
}
