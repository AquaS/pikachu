package com.oee.pikachu.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/11/11.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "survey_result", uniqueConstraints = {@UniqueConstraint(columnNames = {"openId", "surveyId", "questionId"})})
public class SurveyResult extends Entitys implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(32) COMMENT '微信ID'")
    private String openId;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '调研ID'")
    private Long surveyId;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '调研问题ID'")
    private Long questionId;

    @Column(nullable = false, columnDefinition = "varchar(32) COMMENT '所选选项'")
    private String myOption;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
