package com.oee.pikachu.comm.aop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Aqua on 2018/11/11.
 */
@Data
@Component
public class AuthConfig {

    @Value("${LHS.check.user.bind.passUrl}")
    private String toPassUrl;

    @Value("${LHS.mp.app.id}")
    private String appId;

    @Value("${LHS.mp.app.secret}")
    private String appSecret;

    @Value("${LHS.mp.app.redirect.url}")
    private String redirectUrl;

    @Value("${LHS.mp.code.url}")
    private String codeUrlStr;

    @Value("${LHS.mp.openid.url}")
    private String openIdUrl;

    @Value("${LHS.mp.ip.reflect}")
    private String uriList;

    @Value("${LHS.mp.java.url}")
    private String javaUrl;

    private Map<String, String> uriMap;

    @PostConstruct
    private void init() {
        uriMap = Stream.of(uriList.split("\\|")).map(pair -> pair.split(","))
                .collect(Collectors.toMap(tokens -> tokens[0], tokens -> tokens[1]));
    }
}
