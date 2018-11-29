package com.oee.pikachu.domain.VO;

import com.oee.pikachu.domain.Entitys;
import com.oee.pikachu.domain.SurveyQuestionDetail;
import com.oee.pikachu.domain.SurveyQuestionInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 调研问题返回实体
 * Created by Aqua on 2018/11/7.
 */
@Data
public class SurveyQuestion extends Entitys implements Serializable {

    private Long id;

    private Long surveyId;

    private String questionName;

    private String questionType;

    private Date insertTime;

    private Date updateTime;

    private List<SurveyQuestionDetail> questionDetails;

    public static SurveyQuestion fromQuestionInfo(SurveyQuestionInfo info) {
        SurveyQuestion question = new SurveyQuestion();
        BeanUtils.copyProperties(info, question);
        return question;
    }
}
