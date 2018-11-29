package com.oee.pikachu.service.impl;

import com.oee.pikachu.domain.UserPbInfo;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.repository.UserPbInfoRepository;
import com.oee.pikachu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 用戶服務類
 * Created by Aqua on 2018/6/10.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserPbInfoRepository userPbInfoRepository;

    @Override
    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    public Response bindUser(UserPbInfo userPbInfo) {
        try {
            UserPbInfo localUserPbInfo = userPbInfoRepository.findByJobNumber(userPbInfo.getJobNumber());
            if (localUserPbInfo == null) {
                logger.error("this userPbInfo is not exist!");
                return new Response(ExceptionMsg.LoginNameOrPassWordError);
            }
            if (!StringUtils.isEmpty(localUserPbInfo.getOpenId())) {
                logger.info("WorkNum has bind openid : {}", localUserPbInfo.getOpenId());
                return new Response(ExceptionMsg.BindExist);
            } else {
                localUserPbInfo.setOpenId(userPbInfo.getOpenId()).setStatus("bind").setBindingDate(new Date());
                return new Response(ExceptionMsg.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("bind userPbInfo failed, due to error :{}", e);
            return new Response(ExceptionMsg.FAILED);
        }
    }

    @Override
    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    public Response unbindUser(UserPbInfo userPbInfo) {

        try {
            UserPbInfo localUserPbInfo = userPbInfoRepository.findByJobNumber(userPbInfo.getJobNumber());
            if (localUserPbInfo == null) {
                logger.error("this userPbInfo is not exist!");
                return new Response(ExceptionMsg.BindNotExist);
            }
            // TODO:是否需要判断微信ID情况
            if (localUserPbInfo.getOpenId() == null) {
                logger.info("this userPbInfo has no bind exist!");
                return new Response(ExceptionMsg.BindNotExist);
            } else if (localUserPbInfo.getOpenId().equalsIgnoreCase(userPbInfo.getOpenId())){
                localUserPbInfo.setOpenId(null).setStatus("unbind").setUnbindingDate(new Date());
                return new Response(ExceptionMsg.SUCCESS);
            } else {
                logger.info("this userPbInfo has bind openid :{}, param openid :{}", localUserPbInfo.getOpenId(), userPbInfo.getOpenId());
                return new Response(ExceptionMsg.BindExistButNotThisWxid);
            }
        } catch (Exception e) {
            logger.error("bind userPbInfo failed, due to error :{}", e);
            return new Response(ExceptionMsg.FAILED);
        }
    }

    @Override
    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    public Response modifyUserInfo(UserPbInfo userPbInfo) {
        try {
            UserPbInfo localUserPbInfo = userPbInfoRepository.findByJobNumber(userPbInfo.getJobNumber());
            if (localUserPbInfo == null) {
                logger.error("this userPbInfo is not exist!");
                return new Response(ExceptionMsg.LoginNameOrPassWordError);
            }
            if (localUserPbInfo.getOpenId() == null) {
                logger.info("this userPbInfo has no bind exist!");
                return new Response(ExceptionMsg.BindNotExist);
            } else if (localUserPbInfo.getOpenId().equalsIgnoreCase(userPbInfo.getOpenId())) {
                localUserPbInfo.setBirthDate(userPbInfo.getBirthDate()).setCompany(userPbInfo.getCompany()).setRealName(userPbInfo.getRealName())
                        .setNation(userPbInfo.getNation()).setEducation(userPbInfo.getEducation()).setBranchName(userPbInfo.getBranchName())
                        .setPartyDate(userPbInfo.getPartyDate());
                return new Response(ExceptionMsg.SUCCESS);
            } else {
                logger.info("this userPbInfo has bind another wxid, so there is no authority to modify userInfo");
                return new Response(ExceptionMsg.BindExistForbiddenModify);
            }
        } catch (Exception e) {
            logger.error("bind userPbInfo failed, due to error :{}", e);
            return new Response(ExceptionMsg.FAILED);
        }
    }
}
