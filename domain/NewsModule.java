package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 新闻模块
 * Created by Aqua on 2018/11/10.
 */
@Data
@Entity
@Table(name = "news_module", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class NewsModule implements Serializable {
    private static final long serialVersionUID = 7929278290212027205L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '新闻模块标题'")
    private String moduleTitle;

    @Column(nullable = true, columnDefinition = "int COMMENT '模块顺序'")
    private Integer sequence;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '模块状态'")
    private String status;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
