package com.oee.pikachu.comm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Aqua on 2018/10/27.
 */
public class VerifyCodeUtils {

    private static Logger logger = LoggerFactory.getLogger(VerifyCodeUtils.class);

    // 验证码redis key 登录名+VERIFY_CODE_KEY
    private static final String VERIFY_CODE_KEY = "verifycode";

    /**
     * 获取验证码存入redis 的key值
     *
     * @param workNum
     * @return
     */
    public static String getVerifyCodeKey(String workNum) {
        return workNum + VERIFY_CODE_KEY;
    }
}
