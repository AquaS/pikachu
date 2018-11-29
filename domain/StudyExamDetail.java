package com.oee.pikachu.domain;

import lombok.Data;
import lombok.experimental.Accessors;
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
@Accessors(chain = true)
@Table(name = "study_exam_detail", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class StudyExamDetail extends Entitys implements Serializable {
    private static final long serialVersionUID = 2313128179227682250L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '考试id'")
    private Long examId;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '问题id'")
    private Long questionId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '工号'")
    private String jobNumber;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '真实姓名'")
    private String realName;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '我的答案'")
    private String myOption;

    @Column(nullable = true, columnDefinition = "int COMMENT '问题序号'")
    private Integer questionNo;

    @Column(nullable = true, columnDefinition = "int COMMENT '问题序号'")
    private Integer myRight;

    @Column(nullable = true, columnDefinition = "int COMMENT '我的得分'")
    private Integer myGrade;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
