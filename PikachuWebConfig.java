package com.oee.pikachu;

import com.oee.pikachu.comm.aop.AuthConfig;
import com.oee.pikachu.comm.aop.PermissionInterceptor;
import com.oee.pikachu.comm.utils.RedisClientUtil;
import com.oee.pikachu.repository.UserPbInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Aqua on 2018/11/11.
 */
@Configuration
@EnableWebMvc
public class PikachuWebConfig implements WebMvcConfigurer {

    private static final String TRUE = "true";

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Autowired
    private UserPbInfoRepository userPbInfoRepository;

    @Autowired
    private AuthConfig authConfig;

    @Value("${LHS.check.user.bind}")
    private String checkUserBind;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (checkUserBind.equalsIgnoreCase(TRUE)) {
            registry.addInterceptor(new PermissionInterceptor(redisClientUtil, userPbInfoRepository, authConfig));
        }
    }
}
