package com.oee.pikachu.webapi.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;

/**
 * Created by Aqua on 2018/11/4.
 */
public class TokenThread implements Runnable {

    public static String appId = "wx4a42f15a9f758237";

    public static String appSecret = "22463c612df84d2208131f9c00fa6c55";

    //注意是静态的
    public static AccessToken accessToken = null;

    public void run() {
        while (true) {
            try {
                accessToken = this.getAccessToken();
                if (null != accessToken) {
                    System.out.println(accessToken.getAccessToken());
                    Thread.sleep(7000 * 1000); //获取到access_token 休眠7000秒

                } else {
                    Thread.sleep(1000 * 3); //获取的access_token为空 休眠3秒
                }
            } catch (Exception e) {
                System.out.println("发生异常：" + e.getMessage());
                e.printStackTrace();
                try {
                    Thread.sleep(1000 * 10); //发生异常休眠1秒
                } catch (Exception e1) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 获取access_token
     *
     * @return
     */
    private AccessToken getAccessToken() {
        NetWorkHelper netHelper = new NetWorkHelper();
        String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId, appSecret);
        String result = netHelper.getHttpsResponse(Url, "");
        System.out.println(result);
        //response.getWriter().println(result);
        JSONObject json = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        token.setAccessToken(json.getString("access_token"));
        token.setExpiresIn(json.getInteger("expires_in"));
        token.setOpenId(json.getString("openid"));
        MDC.put("openid" + token.getOpenId(), token.getOpenId());
        return token;
    }
}
