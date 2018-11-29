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
@Table(name = "study_exam_question_info")
public class StudyExamQuestionInfo extends Entitys implements Serializable {
    private static final long serialVersionUID = -4213748066782007240L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '考试id'")
    private Long examId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '问题名称'")
    private String questionName;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '问题类型'")
    private String questionType;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '正确答案'")
    private String rightAnswers;

    @Column(nullable = true, columnDefinition = "int COMMENT '参与人数'")
    private Integer joinNumber;

    @Column(nullable = true, columnDefinition = "int COMMENT '答对人数'")
    private Integer rightNumber;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
