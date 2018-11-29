package com.oee.pikachu.webapi;

import com.oee.pikachu.comm.aop.LoggerManage;
import com.oee.pikachu.comm.utils.RedisClientUtil;
import com.oee.pikachu.comm.enums.VerifyCode;
import com.oee.pikachu.comm.utils.VerifyCodeUtils;
import com.oee.pikachu.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * Created by Aqua on 2018/10/25.
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController extends BaseController {

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Autowired
    private AccountService accountService;

    /**
     * 验证码
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "获取验证码")
    public void  getVerifyCode(HttpServletRequest request, HttpServletResponse response ) throws Exception{
        VerifyCode code=new VerifyCode();
        BufferedImage image = code.getImage();
        //将验证码文本存到Redis
        String workNum = request.getParameter("workNum");
        redisClientUtil.setEx(VerifyCodeUtils.getVerifyCodeKey(workNum), code.getText(),60L);
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    /**
     * 验证码文本的标识
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description = "管理后台登陆接口")
    public void login(HttpServletRequest request, HttpServletResponse response ) throws Exception{

        try {
            accountService.login(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
