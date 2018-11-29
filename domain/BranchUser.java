package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 党支部成员
 * Created by Aqua on 2018/11/10.
 */
@Data
@Entity
@Table(name = "branch_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class BranchUser implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "bigint(20) COMMENT '党支部ID'")
    private Long branchId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '成员角色'")
    private String branchRole;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '成员姓名'")
    private String branchUser;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '展示顺序'")
    private String branchOrder;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
