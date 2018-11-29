package com.oee.pikachu.service;

import com.oee.pikachu.domain.result.ResponseData;

/**
 * 党建新闻服务
 * Created by Aqua on 2018/11/10.
 */
public interface NewsService {

    ResponseData queryNewsModule();

    ResponseData queryNewsByModule(Long moduleId);

    ResponseData queryNewsDetail(Long newsId);
}
