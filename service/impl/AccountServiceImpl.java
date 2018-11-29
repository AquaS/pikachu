package com.oee.pikachu.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.oee.pikachu.comm.utils.DesUtils;
import com.oee.pikachu.comm.utils.RedisClientUtil;
import com.oee.pikachu.comm.utils.VerifyCodeUtils;
import com.oee.pikachu.domain.Account;
import com.oee.pikachu.repository.AccountRepository;
import com.oee.pikachu.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Aqua on 2018/10/27.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Value("${lhs.super.account}")
    private String superAccount;

    @Value("${lhs.super.password}")
    private String superPassword;

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String workNum = request.getParameter("workNum");
        String password = request.getParameter("password");
        String vcode = request.getParameter("vcode").toLowerCase();
        Account account = accountRepository.findByWorkNum(workNum);
        if (account == null) {
            logger.info("account :{} is not exist", workNum);
            request.setAttribute("rspMsg", "用户不存在！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (DesUtils.decryptKey(password).equals(account.getPassword())) {
                logger.info("account password is correct, now start to verify code");
                String vc = redisClientUtil.get(VerifyCodeUtils.getVerifyCodeKey(workNum), new TypeReference<String>() {});
                if (vcode.equals(vc.toLowerCase())) {
                    request.getSession().setAttribute("workNum", workNum);
                    request.getRequestDispatcher("welcome.jsp");
                } else {
                    request.getSession().setAttribute("rspMsg", "验证码不正确");
                    request.getRequestDispatcher("login.jsp");
                }
            } else {
                logger.info("workNum :{} password is incorrect", workNum);
                request.setAttribute("rspMsg", "用户密码不正确！");
                request.getRequestDispatcher("login.jsp");
            }
        }
    }
}
