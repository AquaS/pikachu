package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 調研問題信息實體
 * Created by Aqua on 2018/11/6.
 */
@Data
@Entity
@Table(name = "survey_question_info")
public class SurveyQuestionInfo extends Entitys implements Serializable {
    private static final long serialVersionUID = 7537374678929640314L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "bigint COMMENT '調研ID'")
    private Long surveyId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '調研題目'")
    private String questionName;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '調研問題類型'")
    private String questionType;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
