package com.oee.pikachu.webapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.comm.utils.CookieUtils;
import com.oee.pikachu.domain.TopicCommentInfo;
import com.oee.pikachu.domain.TopicInfo;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Aqua on 2018/11/21.
 */
@RestController
@RequestMapping("/topic")
public class TopicController extends BaseController {

    @Autowired
    private TopicService topicService;

    @ResponseBody
    @RequestMapping(value = "/getTopicType")
    @LoggerManage(description = "获取话题类型")
    public ResponseData queryTopicType() {
        return topicService.queryTopicType();
    }

    @ResponseBody
    @RequestMapping(value = "/getTopicList")
    @LoggerManage(description = "获取话题列表")
    public ResponseData queryTopicInfoList() {
        return topicService.queryTopicInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/comment")
    @LoggerManage(description = "用户评论")
    public Response commentTopic(HttpServletRequest request, HttpServletResponse response) {
        try {
            String openid = CookieUtils.getOpenidFromCookie(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String body = IOUtils.readAll(reader);
            TopicCommentInfo commentInfo = JSON.parseObject(body, TopicCommentInfo.class);
            return topicService.commentTopic(commentInfo, openid);
        } catch (Exception e) {
            logger.info("user comment failed, due to error:", e);
            throw new ErrorCodeException(ExceptionMsg.FAILED);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/release")
    @LoggerManage(description = "用户发表话题")
    public Response releaseTopic(HttpServletRequest request, HttpServletResponse response) {
        try {
            String openid = CookieUtils.getOpenidFromCookie(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String body = IOUtils.readAll(reader);
            TopicInfo topicInfo = JSON.parseObject(body, TopicInfo.class);
            return topicService.releaseTopic(topicInfo, openid);
        } catch (Exception e) {
            logger.info("user comment failed, due to error:", e);
            throw new ErrorCodeException(ExceptionMsg.FAILED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/like")
    @LoggerManage(description = "话题点赞")
    public Response likeTopic(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String body = IOUtils.readAll(reader);
            JSONObject questJson = JSON.parseObject(body);
            logger.info("request is:{}", JSON.toJSONString(questJson));
            Long topicId = questJson.getLongValue("topicId");
            return topicService.likeTopic(CookieUtils.getOpenidFromCookie(request), topicId);
        } catch (Exception e) {
            logger.info("user like failed, due to error:", e);
            throw new ErrorCodeException(ExceptionMsg.FAILED);
        }
    }
}
