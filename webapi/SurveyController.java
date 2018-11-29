package com.oee.pikachu.webapi;

import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.domain.SurveyInfo;
import com.oee.pikachu.domain.VO.BaseRequestVO;
import com.oee.pikachu.domain.VO.SurveyQuestion;
import com.oee.pikachu.domain.VO.SurveyRequestVO;
import com.oee.pikachu.domain.VO.VoteRequestVO;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 調研服務接口
 * Created by Aqua on 2018/11/5.
 */
@RestController
@RequestMapping("/survey")
public class SurveyController extends BaseController {

    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/getAllSurvey")
    @LoggerManage(description = "获取調研列表")
    public ResponseData getAllSurvey() {
        try {
            List<SurveyInfo> infoList = surveyService.querySurveyInfoList();
            return new ResponseData(ExceptionMsg.SUCCESS, infoList);
        } catch (Exception e) {
            logger.error("query survey list failed, due to error:", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/getSurveyQuestions")
    @LoggerManage(description = "获取调研问题列表")
    public ResponseData getSurveyQuestions(@RequestBody SurveyInfo surveyInfo) {
        try {
            List<SurveyQuestion> questions = surveyService.querySurveyQuestions(surveyInfo);
            return new ResponseData(ExceptionMsg.SUCCESS, questions);
        } catch (Exception e) {
            logger.error("query survey questions failed, due to error:", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    @LoggerManage(description = "用户投票")
    public Response vote(@RequestBody VoteRequestVO requestVO, HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) {
            logger.error("vote request param error");
            return new Response(ExceptionMsg.ParamError);
        }
        return surveyService.vote(getOpenidFromCookie(request), requestVO.getVoteInfoId(), requestVO.getVoteDetailId());
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @LoggerManage(description = "用户调研问题提交答案")
    public Response submitSurvey(@RequestBody SurveyRequestVO requestVO, HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) {
            logger.error("survey request param error");
            return new Response(ExceptionMsg.ParamError);
        }
        return surveyService.submitSurvey(getOpenidFromCookie(request), requestVO.getSurveyId(), requestVO.getAnswers());
    }

    @RequestMapping(value = "/queryVoteList", method = RequestMethod.POST)
    @LoggerManage(description = "用户调研问题提交答案")
    public ResponseData queryVoteList(@RequestBody BaseRequestVO requestVO) {
        return surveyService.queryVoteInfoList();
    }

    @RequestMapping(value = "/queryVoteDetail", method = RequestMethod.POST)
    @LoggerManage(description = "用户调研问题提交答案")
    public ResponseData queryVoteDetail(@RequestBody VoteRequestVO requestVO) {
        return surveyService.queryVoteDetailByVoteInfo(requestVO.getVoteInfoId());
    }

    private String getOpenidFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String, String> map = new HashMap<>();
        Arrays.stream(cookies).forEach(p -> {
            if (p.getName().equalsIgnoreCase("openid")) {
                map.put(p.getName(), p.getValue());
            }
        });
        return map.get("openid");
    }
}
