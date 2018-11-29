package com.oee.pikachu.service.impl;

import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.domain.*;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.repository.*;
import com.oee.pikachu.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aqua on 2018/11/18.
 */
@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicTypeInfoRepository topicTypeInfoRepository;

    @Autowired
    private TopicInfoRepository topicInfoRepository;

    @Autowired
    private TopicCommentInfoRepository topicCommentInfoRepository;

    @Autowired
    private UserPbInfoRepository userPbInfoRepository;

    @Autowired
    private TopicLikeInfoRepository topicLikeInfoRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    @Override
    public ResponseData queryTopicType() {
        List<TopicTypeInfo> topicTypeInfos = topicTypeInfoRepository.findAll();
        if (CollectionUtils.isEmpty(topicTypeInfos)) {
            log.info("topic type is null");
            return new ResponseData(ExceptionMsg.SUCCESS, Collections.EMPTY_LIST);
        }
        List<TopicInfo> infoList = topicInfoRepository.findAllByStatusOrderByReleaseTimeDesc("");
        if (CollectionUtils.isEmpty(infoList)) {
            log.info("nobody release topic");
            return new ResponseData(ExceptionMsg.SUCCESS, Collections.EMPTY_LIST);
        }
        return new ResponseData(ExceptionMsg.SUCCESS, topicTypeInfos.stream().map(TopicTypeInfo::getTopicType)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseData queryTopicInfo() {
        List<TopicInfo> infoList = topicInfoRepository.findAllByStatusOrderByReleaseTimeDesc("");
        if (CollectionUtils.isEmpty(infoList)) {
            log.info("nobody release topic");
            return new ResponseData(ExceptionMsg.SUCCESS, Collections.EMPTY_LIST);
        }

        return new ResponseData(ExceptionMsg.SUCCESS, infoList);
    }

    @Override
    public Response commentTopic(TopicCommentInfo commentInfo, String openId) {

        UserPbInfo userPbInfo = userPbInfoRepository.findByOpenId(openId);
        if (userPbInfo == null) {
            log.info("user :{} is not exist", openId);
            throw new ErrorCodeException(ExceptionMsg.BindNotExist);
        }
        // 开启事务
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            TopicInfo topicInfo = topicInfoRepository.getOne(commentInfo.getTopicId());
            commentInfo.setJobNumber(userPbInfo.getJobNumber()).setRealName(userPbInfo.getRealName())
                    .setInsertTime(new Date()).setUpdateTime(new Date());
            topicCommentInfoRepository.insertCommentInfo(commentInfo.getTopicId(), userPbInfo.getJobNumber(),
                    userPbInfo.getRealName(), commentInfo.getCommentText());
            topicInfo.setCommentNo(topicInfo.getCommentNo() + 1L);
            topicInfoRepository.updateTopicInfoForUpdate(topicInfo.getId());
        } catch (Exception e) {
            log.info("update topic_info failed due to error:", e);
            transactionManager.rollback(transactionStatus);
            throw new ErrorCodeException(ExceptionMsg.DataBaseError);
        }
        transactionManager.commit(transactionStatus);
        return new Response(ExceptionMsg.SUCCESS);
    }

    @Override
    public Response releaseTopic(TopicInfo topicInfo, String openId) {
        UserPbInfo userPbInfo = userPbInfoRepository.findByOpenId(openId);
        if (userPbInfo == null) {
            log.info("user :{} is not exist", openId);
            throw new ErrorCodeException(ExceptionMsg.BindNotExist);
        }
        try {
            topicInfoRepository.insertTopicInfo(topicInfo.getTopicType(), topicInfo.getTopicName(),
                    userPbInfo.getJobNumber(), userPbInfo.getRealName(), topicInfo.getTopicText());
        } catch (Exception e) {
            log.info("insert topic_type failed, due to error :", e);
            throw new ErrorCodeException(ExceptionMsg.DataBaseError);
        }

        return new Response(ExceptionMsg.SUCCESS);
    }

    @Override
    public Response likeTopic(String openId, Long topicId) {
        UserPbInfo userPbInfo = userPbInfoRepository.findByOpenId(openId);
        if (userPbInfo == null) {
            log.info("user :{} is not exist", openId);
            throw new ErrorCodeException(ExceptionMsg.BindNotExist);
        }
        TopicLikeInfo likeInfo = topicLikeInfoRepository.getByTopicIdAndJobNumber(topicId, userPbInfo.getJobNumber());
        if (likeInfo != null) {
            log.info("user :{} has like topic :{}", userPbInfo.getJobNumber(), topicId);
            throw new ErrorCodeException(ExceptionMsg.HasLikedTopic);
        }
        try {
            topicLikeInfoRepository.insertLikeInfo(topicId, userPbInfo.getJobNumber(), userPbInfo.getRealName());
        } catch (Exception e) {
            log.info("insert topic_like_info failed, due to error :", e);
            throw new ErrorCodeException(ExceptionMsg.DataBaseError);
        }
        return new Response(ExceptionMsg.SUCCESS);
    }
}
