package com.oee.pikachu.comm.aop;

import com.alibaba.fastjson.TypeReference;
import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.comm.utils.RedisClientUtil;
import com.oee.pikachu.domain.UserPbInfo;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.repository.UserPbInfoRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截请求验证账户是否绑定
 * <p>
 * Created by Aqua on 2018/11/11.
 */
@Slf4j
@Data
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private static final String OPENID = "openId_";

    private RedisClientUtil redisClientUtil;

    private UserPbInfoRepository userPbInfoRepository;

    private AuthConfig authConfig;

    public PermissionInterceptor(RedisClientUtil redisClientUtil, UserPbInfoRepository userPbInfoRepository, AuthConfig authConfig) {
        this.redisClientUtil = redisClientUtil;
        this.userPbInfoRepository = userPbInfoRepository;
        this.authConfig = authConfig;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Cookie[] cookies = request.getCookies();
        Map<String, String> map = new HashMap<>();
        if (cookies != null) {
            log.info("cookie is null");
            throw new ErrorCodeException(ExceptionMsg.CookieIsNull);
        }
        Arrays.stream(cookies).forEach(p -> {
            if (p.getName().equalsIgnoreCase("openid")) {
                map.put(p.getName(), p.getValue());
            }
        });
//        if (StringUtils.isEmpty(map.get("openid"))) {
//            NetWorkHelper netHelper = new NetWorkHelper();
//            String openIdUrl = String.format(authConfig.getOpenIdUrl(), authConfig.getAppId(), authConfig.getAppSecret(), code);
//            String openIdResult = netHelper.getHttpsResponse(openIdUrl, "");
//            JSONObject jsonResult = JSON.parseObject(openIdResult);
//            map.put("openid", jsonResult.getString("openid");
//        }

        // 请求URI 绑定接口不校验
        String authUrl = request.getRequestURI();
        if (authConfig.getToPassUrl().contains(authUrl)) {
            return true;
        }
        String jobNumber = getUserJobNumber(map.get("openid"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 验证db、redis是否存在用户
     *
     * @param openId
     * @return
     */
    private String getUserJobNumber(String openId) {
        String existWorkNum = redisClientUtil.get(OPENID + openId, new TypeReference<String>() {
        });
        if (StringUtils.isEmpty(existWorkNum)) {
            UserPbInfo userPbInfo = userPbInfoRepository.findByOpenId(openId);
            if (userPbInfo == null || userPbInfo.getOpenId() == null) {
                log.info("userPbInfo:{} has not bound!", openId);
                throw new ErrorCodeException(ExceptionMsg.BindNotExist);
            }
            redisClientUtil.setEx(OPENID + openId, userPbInfo.getJobNumber(), 30 * 60L);
            return userPbInfo.getJobNumber();
        }
        return existWorkNum;
    }
}
