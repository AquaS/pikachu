package com.oee.pikachu.service.impl;

import com.oee.pikachu.domain.User;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.Response;
import com.oee.pikachu.repository.UserRepository;
import com.oee.pikachu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aqua on 2018/6/10.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    public Response bindUser(User user) {
        try {
            User localUser = userRepository.findByWorkNum(user.getWorkNum());
            if (localUser == null) {
                logger.error("this user is not exist!");
                return new Response(ExceptionMsg.LoginNameOrPassWordError);
            }
            if (localUser.getWxid() != null) {
                logger.info("WorkNum has bind wxid : {}", localUser.getWxid());
                return new Response(ExceptionMsg.BindExist);
            } else {
                localUser.setWxid(user.getWxid());
                return new Response(ExceptionMsg.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("bind user failed, due to error :{}", e);
            return new Response(ExceptionMsg.FAILED);
        }
    }

    @Override
    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    public Response unbindUser(User user) {

        try {
            User localUser = userRepository.findByWorkNum(user.getWxid());
            if (localUser == null) {
                logger.error("this user is not exist!");
                return new Response(ExceptionMsg.BindNotExist);
            }
            // TODO:是否需要判断微信ID情况
            if (localUser.getWxid() == null) {
                logger.info("this user has no bind exist!");
                return new Response(ExceptionMsg.BindNotExist);
            } else if (localUser.getWxid() == user.getWxid()){
                localUser.setWxid(null);
                return new Response(ExceptionMsg.SUCCESS);
            } else {
                logger.info("this user has bind wxid :{}, param wxid :{}", localUser.getWxid(), user.getWxid());
                return new Response(ExceptionMsg.BindExistButNotThisWxid);
            }
        } catch (Exception e) {
            logger.error("bind user failed, due to error :{}", e);
            return new Response(ExceptionMsg.FAILED);
        }
    }

    @Override
    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    public Response modifyUserInfo(User user) {
        try {
            User localUser = userRepository.findByWxid(user.getWxid());
            if (localUser == null) {
                logger.error("this user is not exist!");
                return new Response(ExceptionMsg.LoginNameOrPassWordError);
            }
            if (localUser.getWxid() == null) {
                logger.info("this user has no bind exist!");
                return new Response(ExceptionMsg.BindNotExist);
            } else if (localUser.getWxid() == user.getWxid()) {
                localUser.setBirthDate(user.getBirthDate());
                localUser.setCompany(user.getCompany());
                localUser.setName(user.getName());
                localUser.setEthnicity(user.getEthnicity());
                localUser.setDegree(user.getDegree());
                localUser.setLocatedBranch(user.getLocatedBranch());
                localUser.setPartyDate(user.getPartyDate());
                return new Response(ExceptionMsg.SUCCESS);
            } else {
                logger.info("this user has bind another wxid, so there is no authority to modify userInfo");
                return new Response(ExceptionMsg.BindExistFobbidenMofify);
            }
        } catch (Exception e) {
            logger.error("bind user failed, due to error :{}", e);
            return new Response(ExceptionMsg.FAILED);
        }
    }
}
