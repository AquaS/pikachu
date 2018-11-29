package com.oee.pikachu.service;

import com.oee.pikachu.domain.result.ResponseData;

/**
 * 党支部服务
 * Created by Aqua on 2018/11/10.
 */
public interface BranchService {

    /**
     * 查询所属党支部信息
     *
     * @param openId
     * @return
     */
    ResponseData branchIntro(String openId);

    /**
     * 查询党支部成员信息
     *
     * @param openId
     * @return
     */
    ResponseData branchUser(String openId);
}
