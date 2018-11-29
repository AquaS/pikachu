package com.oee.pikachu.service.impl;

import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.domain.News;
import com.oee.pikachu.domain.NewsModule;
import com.oee.pikachu.domain.enums.ExceptionMsg;
import com.oee.pikachu.domain.result.ResponseData;
import com.oee.pikachu.repository.NewsModuleRepository;
import com.oee.pikachu.repository.NewsRepository;
import com.oee.pikachu.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Aqua on 2018/11/10.
 */
@Slf4j
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsModuleRepository newsModuleRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public ResponseData queryNewsModule() {
        List<NewsModule> newsModules = newsModuleRepository.queryAllByStatus("1");
        if (CollectionUtils.isEmpty(newsModules)) {
            log.info("no newsModule is available");
            throw new ErrorCodeException(ExceptionMsg.NewsModuleNotExist);
        }
        return new ResponseData(ExceptionMsg.SUCCESS, newsModules);
    }

    @Override
    public ResponseData queryNewsByModule(Long moduleId) {
        if (moduleId == null) {
            log.info("param is not correct");
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }
        List<News> news = newsRepository.queryAllByModuleId(moduleId);

        if (CollectionUtils.isEmpty(news)) {
            log.info("no news exist in this module:{}", moduleId);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        news.stream().forEach(p -> p.setContent(null));
        return new ResponseData(ExceptionMsg.SUCCESS, news);
    }

    @Override
    public ResponseData queryNewsDetail(Long newsId) {
        if (newsId == null) {
            log.info("params is not correct");
            throw new ErrorCodeException(ExceptionMsg.ParamError);
        }
        News news = newsRepository.queryById(newsId);
        if (news == null) {
            log.info("news is not exist by newsId:{}", newsId);
            throw new ErrorCodeException(ExceptionMsg.NewsNotExist);
        }
        return new ResponseData(ExceptionMsg.SUCCESS, news);
    }
}
