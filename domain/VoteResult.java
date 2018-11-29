package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * Created by Aqua on 2018/11/10.
 */
@Data
@Entity
@Table(name = "vote_result", uniqueConstraints = {@UniqueConstraint(columnNames = {"openId", "voteInfoId", "voteDetailId"})})
public class VoteResult extends Entitys implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(32) COMMENT '微信ID'")
    private String openId;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '投票ID'")
    private Long voteInfoId;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '投票对象ID'")
    private Long voteDetailId;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
