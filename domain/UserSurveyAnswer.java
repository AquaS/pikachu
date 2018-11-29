package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用戶调研问题答案
 * Created by Aqua on 2018/11/7.
 */
@Data
@Entity
@Table(name = "user_survey_answer", uniqueConstraints = {@UniqueConstraint(columnNames = {"questionId", "openId"})})
public class UserSurveyAnswer extends Entitys implements Serializable {
    private static final long serialVersionUID = -7646332266275132624L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "bigint(20) COMMENT '調研ID'")
    private Long surveyId;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '微信ID'")
    private Long openId;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '问题ID'")
    private Long questionId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '用户答案'")
    private String answer;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
