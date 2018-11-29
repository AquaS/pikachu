package com.oee.pikachu.service.impl;

import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.domain.BranchIntro;
import com.oee.pikachu.domain.BranchUser;
import com.oee.pikachu.domain.UserPbInfo;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.repository.BranchIntroRepository;
import com.oee.pikachu.repository.BranchManageRepository;
import com.oee.pikachu.repository.BranchUserRepository;
import com.oee.pikachu.repository.UserPbInfoRepository;
import com.oee.pikachu.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 党支部服务实现类
 * Created by Aqua on 2018/11/10.
 */
@Slf4j
@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private UserPbInfoRepository userPbInfoRepository;

    @Autowired
    private BranchManageRepository branchManageRepository;

    @Autowired
    private BranchUserRepository branchUserRepository;

    @Autowired
    private BranchIntroRepository branchIntroRepository;

    @Override
    public ResponseData branchIntro(String openId) {

        UserPbInfo userPbInfo = userPbInfoRepository.findByOpenId(openId);
        if (userPbInfo == null) {
            log.info("userPbInfo :{} is not bind or no branch", openId);
            throw new ErrorCodeException(ExceptionMsg.BindNotExistOrNoBranch);
        }
        BranchIntro branch = branchIntroRepository.findByBranchName(userPbInfo.getBranchName());
        if (branch == null) {
            log.info("userPbInfo :{} branch is not exist", openId);
            throw new ErrorCodeException(ExceptionMsg.BranchIsNotExist);
        }
        return new ResponseData(ExceptionMsg.SUCCESS, branch);
    }

    @Override
    public ResponseData branchUser(String openId) {
        UserPbInfo userPbInfo = userPbInfoRepository.findByOpenId(openId);
        if (userPbInfo == null || userPbInfo.getBranchName() == null) {
            log.info("userPbInfo :{} is not bind or no branch", openId);
            throw new ErrorCodeException(ExceptionMsg.BindNotExistOrNoBranch);
        }
        BranchIntro branch = branchIntroRepository.findByBranchName(userPbInfo.getBranchName());
        List<BranchUser> branchUsers = branchUserRepository.queryAllByBranchId(branch.getId());
        if (CollectionUtils.isEmpty(branchUsers)) {
            log.info("no userPbInfo in this branch:{}", userPbInfo.getBranchName());
        }
        return new ResponseData(ExceptionMsg.SUCCESS, branchUsers);
    }
}
