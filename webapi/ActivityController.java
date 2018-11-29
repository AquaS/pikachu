package com.oee.pikachu.webapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.comm.utils.CookieUtils;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService activityService;

    @ResponseBody
    @RequestMapping(value = "/getActivityList")
    @LoggerManage(description = "获取活动列表")
    public ResponseData queryActivityList(HttpServletRequest request, HttpServletResponse response) {

        return activityService.queryActivityList();
    }

    @ResponseBody
    @RequestMapping(value = "/getActivityDetail")
    @LoggerManage(description = "获取活动详情")
    public ResponseData queryActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtils.readAll(reader);
        JSONObject questJson = JSON.parseObject(body);
        logger.info("request is:{}", JSON.toJSONString(questJson));
        Long activityId = questJson.getLongValue("activityId");
        return activityService.queryActivityDetail(activityId);
    }

    @ResponseBody
    @RequestMapping(value = "/enroll")
    @LoggerManage(description = "活动报名")
    public Response enrollActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtils.readAll(reader);
        JSONObject questJson = JSON.parseObject(body);
        logger.info("request is:{}", JSON.toJSONString(questJson));
        Long activityId = questJson.getLongValue("activityId");
        return activityService.enrollActivity(CookieUtils.getOpenidFromCookie(request), activityId);
    }
}
