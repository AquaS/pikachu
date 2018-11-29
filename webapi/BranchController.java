package com.oee.pikachu.webapi;

import com.alibaba.fastjson.JSON;
import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.comm.utils.CookieUtils;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aqua on 2018/11/15.
 */
@RestController
@RequestMapping("/branch")
public class BranchController extends BaseController {

    @Autowired
    private BranchService branchService;

    @RequestMapping(value = "/getBranchIntro")
    @LoggerManage(description = "获取党支部简介")
    public ResponseData getBranchIntro(HttpServletRequest request, HttpServletResponse response) {
        logger.info("request params :{}", request);
        return branchService.branchIntro(CookieUtils.getOpenidFromCookie(request));
    }


    @RequestMapping(value = "/getBranchUser")
    @LoggerManage(description = "获取党支部成员")
    public ResponseData getBranchUser(HttpServletRequest request, HttpServletResponse response) {
        logger.info("request params :{}", request);
        return branchService.branchUser(CookieUtils.getOpenidFromCookie(request));
    }
}
