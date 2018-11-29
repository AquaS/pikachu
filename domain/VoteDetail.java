package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 投票详情
 * Created by Aqua on 2018/11/10.
 */
@Data
@Entity
@Table(name = "vote_detail", uniqueConstraints = {@UniqueConstraint(columnNames = {"voteId", "jobNumber"})})
public class VoteDetail extends Entitys implements Serializable {
    private static final long serialVersionUID = -2629749739548285757L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '投票ID'")
    private Long voteId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '投票工号'")
    private String jobNumber;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '真实姓名'")
    private String realName;

    @Column(nullable = true, columnDefinition = "int COMMENT '投票人数'")
    private Integer voteNo;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
