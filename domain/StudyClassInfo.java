package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/11/27.
 */
@Data
@Entity
@Table(name = "study_class_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class StudyClassInfo extends Entitys implements Serializable {

    private static final long serialVersionUID = -7113403273817810175L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '考试模块id'")
    private Long studyModuleId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '考试模块子类'")
    private String studyClass;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
