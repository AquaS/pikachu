package com.oee.pikachu.webapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.oee.pikachu.comm.aop.AuthConfig;
import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.comm.utils.CookieUtils;
import com.oee.pikachu.domain.UserPbInfo;
import com.oee.pikachu.domain.UserPoints;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.repository.UserPointsRepository;
import com.oee.pikachu.repository.UserPbInfoRepository;
import com.oee.pikachu.service.UserService;
import com.oee.pikachu.webapi.servlet.NetWorkHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description:
 * @Auth: yangyanming
 * @Date: 2018/6/9
 * @Version: 1.0
 **/

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserPbInfoRepository userPbInfoRepository;

    @Autowired
    private UserPointsRepository userPointsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthConfig authConfig;

    @RequestMapping(value = "/getUserInfo")
    @LoggerManage(description = "获取用户信息")
    public ResponseData getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        UserPbInfo userPbInfo = new UserPbInfo();
        if (request.getCookies() == null) {
            logger.error("getUserPoints request param error");
            return new ResponseData(ExceptionMsg.ParamError);
        }
        userPbInfo.setOpenId(CookieUtils.getOpenidFromCookie(request));
        logger.info("Request param wxid is :{}", userPbInfo.getOpenId());
        try {
            UserPbInfo localUserPbInfo = userPbInfoRepository.findByOpenId(userPbInfo.getOpenId());
            if (localUserPbInfo == null) {
                return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
            }
            return new ResponseData(ExceptionMsg.SUCCESS, localUserPbInfo);
        } catch (Exception e) {
            logger.info("get userInfo failed, due to error :{}", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/getUserPoints", method = RequestMethod.POST)
    @LoggerManage(description = "获取用户每月积分")
    public ResponseData getUserPoints(@RequestBody UserPoints user, HttpServletRequest request, HttpServletResponse response) {
        if (user == null || request.getCookies() == null) {
            logger.error("getUserPoints request param error");
            return new ResponseData(ExceptionMsg.ParamError);
        }
        user.setOpenId(CookieUtils.getOpenidFromCookie(request));
        logger.info("Request param is :{}", user.toString());

        try {
            List<UserPoints> userPoints = new ArrayList<>();
            if (user.getDate() != null) {
                userPoints = userPointsRepository.findByOpenIdAndDateGreaterThanEqual(user.getOpenId(), user.getDate());
            } else {
                userPoints = userPointsRepository.findByOpenId(user.getOpenId());
            }
            if (userPoints == null || userPoints.size() < 1) {
                logger.info("This user has no points");
                return new ResponseData(ExceptionMsg.UserHasNoPoints);
            }
            return new ResponseData(ExceptionMsg.SUCCESS, userPoints);
        } catch (Exception e) {
            logger.error("get user points failed, due to error :{}", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/bind")
    @LoggerManage(description = "绑定用户微信")
    public Response bindUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtils.readAll(reader);
        JSONObject questJson = JSON.parseObject(body);
        logger.info("request is:{}", JSON.toJSONString(questJson));
        String realName = questJson.getString("name");
        String jobNumber = questJson.getString("workNum");
        if (realName == null || jobNumber == null) {
            logger.error("bindUser request param error");
            return new Response(ExceptionMsg.ParamError);
        }
        UserPbInfo userPbInfo = new UserPbInfo();
        userPbInfo.setRealName(realName).setJobNumber(jobNumber);
        userPbInfo.setOpenId(CookieUtils.getOpenidFromCookie(request));
        return userService.bindUser(userPbInfo);
    }

    @RequestMapping(value = "/unbind", method = RequestMethod.POST)
    @LoggerManage(description = "解绑用户微信")
    public Response unbindUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtils.readAll(reader);
        JSONObject questJson = JSON.parseObject(body);
        logger.info("request is:{}", JSON.toJSONString(questJson));
        String realName = questJson.getString("name");
        String jobNumber = questJson.getString("workNum");
        if (jobNumber == null || request.getCookies() == null) {
            logger.error("unbindUser request param error");
            return new Response(ExceptionMsg.ParamError);
        }
        UserPbInfo userPbInfo = new UserPbInfo();
        userPbInfo.setOpenId(CookieUtils.getOpenidFromCookie(request)).setJobNumber(jobNumber);
        return userService.unbindUser(userPbInfo);
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    @LoggerManage(description = "修改用户信息")
    public Response modifyUserInfo(@RequestBody UserPbInfo userPbInfo, HttpServletRequest request, HttpServletResponse response) {
        if (userPbInfo == null || request.getCookies() == null) {
            logger.error("unbindUser request param error");
            return new Response(ExceptionMsg.ParamError);
        }
        userPbInfo.setOpenId(CookieUtils.getOpenidFromCookie(request));
        return userService.modifyUserInfo(userPbInfo);
    }

    @RequestMapping(value = "/test")
    @LoggerManage(description = "测试")
    public void testStart() {
        System.out.println("hello world!");
    }

    @RequestMapping(value = "/getUserCode/{uriIndex:[0-9\\\\.]+}")
    public void getCode(@PathVariable("uriIndex")String uriIndex, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> uriMap = authConfig.getUriMap();
        NetWorkHelper netHelper = new NetWorkHelper();
        String redirectUri = null;
        try {
            redirectUri = URLEncoder.encode(authConfig.getRedirectUrl() + uriMap.get(uriIndex), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String codeUrl = String.format(authConfig.getCodeUrlStr(), redirectUri, authConfig.getAppId());
        String codeResult = netHelper.getHttpsResponse(codeUrl, "");
        try {
//            request.getRequestDispatcher(uriMap.get(uriIndex)).forward(request, response);
            response.sendRedirect("http://mptest.intecredit.cn/views/branch_generalization.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getUserOpenid")
    public void getOpenid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtils.readAll(reader);
        JSONObject questJson = JSON.parseObject(body);
        String code = questJson.getString("code");
        NetWorkHelper netHelper = new NetWorkHelper();
        logger.info("request code is :{}", code);
        if (code == null ) {
            code = request.getParameter("code");
        }
        String openidUrl = String.format(authConfig.getOpenIdUrl(), authConfig.getAppId(), authConfig.getAppSecret(), code);
        String openidResult = netHelper.getHttpsResponse(openidUrl, "");
        JSONObject json = JSON.parseObject(openidResult);
        // 将openid写入cookie
        logger.info("mp server response is :{}", openidResult);
        String openid = json.getString("openid");
        Cookie cookie = new Cookie("openid", openid);
        cookie.setDomain(authConfig.getRedirectUrl().split("//")[1]);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);

    }
}
