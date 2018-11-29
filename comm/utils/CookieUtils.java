package com.oee.pikachu.comm.utils;

import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aqua on 2018/11/15.
 */
@Slf4j
public final class CookieUtils {

    public static String getOpenidFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            log.info("cookie is null");
            throw new ErrorCodeException(ExceptionMsg.CookieIsNull);
        }
        Map<String, String> map = new HashMap<>();
        Arrays.stream(cookies).forEach(p -> {
            if (p.getName().equalsIgnoreCase("openid")) {
                map.put(p.getName(), p.getValue());
            }
        });
        return map.get("openid");
    }
}
