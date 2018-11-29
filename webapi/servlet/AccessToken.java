package com.oee.pikachu.webapi.servlet;

import lombok.Data;

/**
 * Created by Aqua on 2018/11/4.
 */
@Data
public class AccessToken {

    private int expiresIn;

    private String accessToken;

    private String openId;
}
