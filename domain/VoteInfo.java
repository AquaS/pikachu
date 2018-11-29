package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 投票实体
 * Created by Aqua on 2018/11/10.
 */
@Data
@Entity
@Table(name = "vote_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class VoteInfo extends Entitys implements Serializable {
    private static final long serialVersionUID = 9041911902516135808L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '投票名称'")
    private String voteName;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '开始时间'")
    private Date beginTime;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '截至时间'")
    private Date endTime;

    @Column(nullable = true, columnDefinition = "int COMMENT '投票人数'")
    private Integer voteNo;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '状态'")
    private String status;

    @Column(nullable = true, columnDefinition = "text COMMENT '投票介绍'")
    private String voteMsg;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
