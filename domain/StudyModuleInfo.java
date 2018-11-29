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
@Table(name = "study_module_info")
public class StudyModuleInfo extends Entitys implements Serializable {

    private static final long serialVersionUID = 6687860803779816766L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '模块顺序'")
    private Long studyOrder;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '学习模块'")
    private String studyModule;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
