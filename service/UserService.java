package com.oee.pikachu.service;

import com.oee.pikachu.domain.UserPbInfo;
import com.oee.pikachu.domain.result.Response;

/**
 * 用戶服務
 * Created by Aqua on 2018/6/10.
 */
public interface UserService {

    /**
     * 绑定用户微信
     * @param userPbInfo
     */
    Response bindUser(UserPbInfo userPbInfo);

    /**
     * 解除绑定
     * @param userPbInfo
     * @return
     */
    Response unbindUser(UserPbInfo userPbInfo);

    /**
     * 修改用户信息
     * @param userPbInfo
     * @return
     */
    Response modifyUserInfo(UserPbInfo userPbInfo);
}
