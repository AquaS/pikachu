package com.oee.pikachu.webapi.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aqua on 2018/11/4.
 */
@Slf4j
@WebServlet(name = "accessTokenServlet", urlPatterns = "/wechatAccessToken")
public class AccessTokenServlet extends HttpServlet {

    @PostConstruct
    public void init() throws ServletException {
//        TokenThread.appId = getInitParameter("appid");  //获取servlet初始参数appid和appsecret
//        TokenThread.appSecret = getInitParameter("appsecret");
        log.info("appid:{}, appSecret:{}", TokenThread.appId, TokenThread.appSecret);
        new Thread(new TokenThread()).start(); //启动进程
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
