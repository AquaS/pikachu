package com.oee.pikachu.service;

import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;

/**
 * Created by Aqua on 2018/11/8.
 */
public interface ActivityService {

    /**
     * 查询活动列表
     *
     * @return
     */
    ResponseData queryActivityList();

    /**
     * 查询活动详情
     *
     * @param activityId
     * @return
     */
    ResponseData queryActivityDetail(Long activityId);

    /**
     * 活动报名
     *
     * @param openId
     * @return
     */
    Response enrollActivity(String openId, Long activityId);
}
