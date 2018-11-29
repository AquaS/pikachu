package com.oee.pikachu.webapi;

import com.alibaba.fastjson.JSON;
import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.domain.VO.BaseRequestVO;
import com.oee.pikachu.domain.VO.NewsListRequestVO;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 * Created by Aqua on 2018/11/11.
 */
@RestController
@RequestMapping("/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/getNewsModules")
    @LoggerManage(description = "获取新闻模块列表")
    public ResponseData queryNewsModule() {
        return newsService.queryNewsModule();
    }

    @RequestMapping(value = "/getModuleNews")
    @LoggerManage(description = "获取模块新闻列表")
    public ResponseData queryNewsInModule(@RequestBody NewsListRequestVO requestVO) {
        return newsService.queryNewsByModule(requestVO.getModuleId());
    }

    @RequestMapping(value = "/getNewDetail")
    @LoggerManage(description = "获取模块新闻详情")
    public ResponseData queryNewsDetail(@RequestBody NewsListRequestVO requestVO) {
        return newsService.queryNewsDetail(requestVO.getNewId());
    }
}
