package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 党支部
 * Created by Aqua on 2018/11/10.
 */
@Data
@Entity
@Table(name = "branch_manage", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class BranchManage extends Entitys implements Serializable {
    private static final long serialVersionUID = 2105877568324868644L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '党支部简称'")
    private String branchTitle;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '党支部名称'")
    private String branchName;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
