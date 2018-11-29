package com.oee.pikachu.repository;

import com.oee.pikachu.domain.SurveyResult;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aqua on 2018/11/11.
 */
public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {

    SurveyResult findByOpenIdAndSurveyIdAndQuestionId(String openId, Long surveyId, Long questionId);

    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    @Query(value = "insert into survey_result(open_id, survey_id, question_id, my_option, insert_time, update_time) VALUES (?1, ?2, ?3, ?4, now(), now())", nativeQuery = true)
    void insertSurveyResult(String openId, Long surveyId, Long questionId, String myOption);

    @Override
    default <S extends SurveyResult> List<S> saveAll(Iterable<S> iterable) {
        List<S> resultList = new ArrayList<>();
        iterable.forEach(p ->{
            insertSurveyResult(p.getOpenId(), p.getSurveyId(), p.getQuestionId(), p.getMyOption());
            resultList.add(p);
        });
        return resultList;
    }
}
