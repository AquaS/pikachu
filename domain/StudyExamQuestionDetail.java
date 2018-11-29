package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/11/08.
 */
@Data
@Entity
@Table(name = "study_exam_question_detail")
public class StudyExamQuestionDetail extends Entitys implements Serializable {
    private static final long serialVersionUID = -4441577402149722320L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '问题id'")
    private Long questionId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '选项'")
    private String optionNo;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '选项内容'")
    private String optionName;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
