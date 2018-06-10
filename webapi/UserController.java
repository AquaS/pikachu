package com.oee.pikachu.webapi;

import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.domain.User;
import com.oee.pikachu.domain.UserPoints;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.repository.UserPointsRepository;
import com.oee.pikachu.repository.UserRepository;
import com.oee.pikachu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auth: yangyanming
 * @Date: 2018/6/9
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPointsRepository userPointsRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @LoggerManage(description = "获取用户信息")
    public ResponseData getUserInfo(@RequestBody User user) {
        if (user == null || user.getWxid() == null) {
            logger.error("getUserPoints request param error");
            return new ResponseData(ExceptionMsg.ParamError);
        }
        logger.info("Request param wxid is :{}", user.getWxid());

        try {
            User localUser = userRepository.findByWxid(user.getWxid());
            if (localUser == null) {
                return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
            }
            return new ResponseData(ExceptionMsg.SUCCESS, user);
        } catch (Exception e) {
            logger.info("get userInfo failed, due to error :{}", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/getUserPoints", method = RequestMethod.POST)
    @LoggerManage(description = "获取用户每月积分")
    public ResponseData getUserPoints(@RequestBody UserPoints user) {
        if (user == null || user.getWxid() == null) {
            logger.error("getUserPoints request param error");
            return new ResponseData(ExceptionMsg.ParamError);
        }

        logger.info("Request param is :{}", user.toString());

        try {
            List<UserPoints> userPoints = new ArrayList<>();
            if (user.getDate() != null) {
                userPoints = userPointsRepository.findByWxidAndDateGreaterThanEqual(user.getWxid(), user.getDate());
            } else {
                userPoints = userPointsRepository.findByWxid(user.getWxid());
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

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    @LoggerManage(description = "绑定用户微信")
    public Response bindUser(@RequestBody User user) {
        if (user == null || user.getWorkNum() == null) {
            logger.error("bindUser request param error");
            return new Response(ExceptionMsg.ParamError);
        }
        return userService.bindUser(user);
    }

    @RequestMapping(value = "/unbind", method = RequestMethod.POST)
    @LoggerManage(description = "解绑用户微信")
    public Response unbindUser(@RequestBody User user) {
        if (user == null || user.getWxid() == null) {
            logger.error("unbindUser request param error");
            return new Response(ExceptionMsg.ParamError);
        }
        return userService.unbindUser(user);
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    @LoggerManage(description = "修改用户信息")
    public Response modifyUserInfo(@RequestBody User user) {
        if (user == null || user.getWxid() == null) {
            logger.error("unbindUser request param error");
            return new Response(ExceptionMsg.ParamError);
        }
        return userService.modifyUserInfo(user);
    }

    @RequestMapping(value = "/test")
    @LoggerManage(description = "测试")
    public void testStart() {
        System.out.println("hello world!");
    }
}
