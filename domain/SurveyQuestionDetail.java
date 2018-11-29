package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/11/7.
 */
@Data
@Entity
@Table(name = "survey_question_detail")
public class SurveyQuestionDetail extends Entitys implements Serializable {
    private static final long serialVersionUID = 7668032981158994897L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '調研ID'")
    private Long surveyId;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '調研問題ID'")
    private Long questionId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '調研問題選項'")
    private String optionNo;

    @Column(nullable = true, columnDefinition = "varchar(64) COMMENT '調研問題'")
    private String optionName;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '調研問題順序'")
    private Integer optionNumber;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
