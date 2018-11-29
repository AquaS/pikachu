package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 調研對象
 * Created by Aqua on 2018/11/5.
 */
@Data
@Entity
@Table(name = "survey_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"surveyNo"})})
public class SurveyInfo implements Serializable {

    private static final long serialVersionUID = -533043333775739968L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '調研名稱'")
    private String surveyName;

    @Column(nullable = true)
    private Date beginTime;

    @Column(nullable = true)
    private Date endTime;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '調研名稱'")
    private Integer surveyNo;

    @Column(nullable = true)
    private String status;

    @Column(nullable = true)
    private String surveyMsg;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
