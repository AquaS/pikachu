package com.oee.pikachu.service.impl;

import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.domain.ActivityDetail;
import com.oee.pikachu.domain.ActivityInfo;
import com.oee.pikachu.domain.UserPbInfo;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.repository.ActivityDetailRepository;
import com.oee.pikachu.repository.ActivityInfoRepository;
import com.oee.pikachu.repository.UserPbInfoRepository;
import com.oee.pikachu.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Aqua on 2018/11/8.
 */
@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityInfoRepository infoRepository;

    @Autowired
    private ActivityDetailRepository detailRepository;

    @Autowired
    private UserPbInfoRepository userPbInfoRepository;

    @Override
    public ResponseData queryActivityList() {
        List<ActivityInfo> infoList = infoRepository.findAll();
        if (CollectionUtils.isEmpty(infoList)) {
            log.info("no activity exist now");
            throw new ErrorCodeException(ExceptionMsg.NoActivity);
        }
        return new ResponseData(ExceptionMsg.SUCCESS, infoList);
    }

    @Override
    public ResponseData queryActivityDetail(Long activityId) {
        if (activityId == null) {
            log.error("queryActivityDetail param error");
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }
        ActivityInfo activityInfo = infoRepository.getById(activityId);
        return new ResponseData(ExceptionMsg.SUCCESS, activityInfo);
    }

    @Override
    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    public Response enrollActivity(String openId, Long activityId) {
        if (openId == null || activityId == null) {
            log.error("enrollActivity param error");
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }
        UserPbInfo userPbInfo = userPbInfoRepository.findByOpenId(openId);
        if (userPbInfo == null) {
            log.info("user :{} is not exist", openId);
            throw new ErrorCodeException(ExceptionMsg.BindNotExist);
        }

        ActivityDetail enrolled = detailRepository.getByActivityIdAndJobNumber(activityId, userPbInfo.getJobNumber());
        if (enrolled != null) {
            log.info("user:{} has enrolled activity:{}", userPbInfo.getJobNumber(), activityId);
            throw new ErrorCodeException(ExceptionMsg.HasEnrolled);
        }
        ActivityInfo activity = infoRepository.getById(activityId);
        if (activity.getJoinNumber() < activity.getMaxNumber()) {
            detailRepository.insertActivityDetail(activityId, userPbInfo.getJobNumber(), userPbInfo.getRealName());
            activity.setJoinNumber(activity.getJoinNumber() + 1);
        } else {
            log.info("beyond activity max number");
            throw new ErrorCodeException(ExceptionMsg.BeyondActivityLimit);
        }

        return new Response(ExceptionMsg.SUCCESS);
    }
}
