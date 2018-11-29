package com.oee.pikachu.service;

import com.oee.pikachu.domain.TopicCommentInfo;
import com.oee.pikachu.domain.TopicInfo;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;

/**
 * Created by Aqua on 2018/11/18.
 */
public interface TopicService {

    /**
     * 查询话题类型列表
     *
     * @return
     */
    ResponseData queryTopicType();

    /**
     * 查询话题详情
     *
     * @return
     */
    ResponseData queryTopicInfo();

    /**
     * 话题评论
     *
     * @param commentInfo
     * @param openId
     * @return
     */
    Response commentTopic(TopicCommentInfo commentInfo, String openId);

    /**
     * 发布话题
     *
     * @param topicInfo
     * @return
     */
    Response releaseTopic(TopicInfo topicInfo, String openId);

    /**
     * 话题点赞
     *
     * @param openId
     * @param topicId
     * @return
     */
    Response likeTopic(String openId, Long topicId);
}
