package com.oee.pikachu.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/10/24.
 */
@Data
@Entity
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(columnNames = {"workNum"})})
public class Account extends Entitys implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '密码'")
    private String password;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '工号'")
    private String workNum;

    @Column(nullable = false, columnDefinition = "tinyint default 0 COMMENT '启用状态'")
    private int state;

    @CreatedDate
    @Column(nullable = false)
    private Date createAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}
