package com.oee.pikachu.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Aqua on 2018/6/10.
 */
@Data
@Entity
@Table(name = "user_points", uniqueConstraints = {@UniqueConstraint(columnNames = {"openId", "date"})})
public class UserPoints extends Entitys implements Serializable {
    private static final long serialVersionUID = 1056186659135793327L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '微信id'")
    private String openId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, columnDefinition = "int default 0 COMMENT '本月积分'")
    private int points;

    @Column(nullable = false, columnDefinition = "int default 0 COMMENT '本月消费积分'")
    private int consumePoints;

    // 是否失效
    @Column(nullable = false, columnDefinition = "int default 0 COMMENT '本月积分是否失效'")
    private int state;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;
}
