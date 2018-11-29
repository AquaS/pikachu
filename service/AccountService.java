package com.oee.pikachu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aqua on 2018/10/24.
 */
public interface AccountService {

    void login(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
