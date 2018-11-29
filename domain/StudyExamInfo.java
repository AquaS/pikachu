package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/11/17.
 */
@Data
@Entity
@Table(name = "study_exam_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class StudyExamInfo extends Entitys implements Serializable {

    private static final long serialVersionUID = 8464543996277993368L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '考试名称'")
    private String studyExamName;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '模块一'")
    private String moduleOne;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '模块二'")
    private String moduleTwo;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '開始日期'")
    private Date beginTime;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '結束日期'")
    private Date endTime;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '參與人數'")
    private Integer joinNumber;

    @Column(nullable = true, columnDefinition = "int COMMENT '学习模块'")
    private Integer oneMark;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '考試狀態'")
    private String status;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '学习模块'")
    private String pass;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
