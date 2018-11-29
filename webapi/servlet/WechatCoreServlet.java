package com.oee.pikachu.webapi.servlet;

import com.oee.pikachu.comm.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信請求處理的核心類
 * Created by Aqua on 2018/11/4.
 */
@Slf4j
@WebServlet(name = "wechatCoreServlet", urlPatterns = "/wechatSign")
public class WechatCoreServlet extends HttpServlet {

    /**
     * 請求校驗來自微信服務器
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("=======开始请求校验======");

        // 微信加密签名.
        String signature = request.getParameter("signature");
        log.info("signature===={}", signature);
        // 时间戳.
        String timestamp = request.getParameter("timestamp");
        log.info("timestamp===={}", timestamp);
        // 随机数.
        String nonce = request.getParameter("nonce");
        log.info("nonce===={}", nonce);
        // 随机字符串.
        String echostr = request.getParameter("echostr");
        log.info("echostr===={}", echostr);

        PrintWriter out = response.getWriter();

        // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败.
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            log.info("=======请求校验成功======{}", echostr);
            out.print(echostr);
        }

        out.close();
        out = null;
    }
}
