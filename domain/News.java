package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 新闻实体
 * Created by Aqua on 2018/11/10.
 */
@Data
@Entity
@Table(name = "news", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class News implements Serializable {
    private static final long serialVersionUID = -4739536450573447733L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '新闻标题'")
    private String title;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '新闻类型'")
    private String type;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '模块ID'")
    private Long moduleId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '模块名称'")
    private String moduleName;

    @Column(nullable = true, columnDefinition = "text COMMENT '新闻内容'")
    private String content;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
