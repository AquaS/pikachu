package com.oee.pikachu.service;

import com.oee.pikachu.domain.User;
import com.oee.pikachu.domain.result.Response;

/**
 * Created by Aqua on 2018/6/10.
 */
public interface UserService {

    /**
     * 绑定用户微信
     * @param user
     */
    Response bindUser(User user);

    /**
     * 解除绑定
     * @param user
     * @return
     */
    Response unbindUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Response modifyUserInfo(User user);
}
