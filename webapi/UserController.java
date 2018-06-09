package com.oee.pikachu.webapi;

import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.domain.User;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @LoggerManage(description = "获取用户信息")
    public ResponseData getUserInfo(@RequestBody User wxid) {
        logger.info("Request parama wxid is :{}", wxid);

        try {
            User user = userRepository.findByWxid(wxid.getWxid());
            if (user == null) {
                return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
            }
            return new ResponseData(ExceptionMsg.SUCCESS, user);
        } catch (Exception e) {
            logger.info("get userInfo failed, due to error :{}", e.getMessage());
            return new ResponseData(e.getMessage());
        }
    }
}
