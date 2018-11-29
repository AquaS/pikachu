package com.oee.pikachu.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Auth: yangyanming
 * @Date: 2018/6/9
 * @Version: 1.0
 **/
@Data
@Entity
@Accessors(chain = true)
@Table(name = "user_pb_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"jobNumber"})})
public class UserPbInfo extends Entitys implements Serializable {

    private static final long serialVersionUID = -9075774567020210970L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '微信ID'")
    private String openId;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '姓名'")
    private String realName;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '性别'")
    private String sex;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '民族'")
    private String nation;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '职务'")
    private String rank;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '手机号码'")
    private String myPhone;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '学历'")
    private String education;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '公司名称'")
    private String company;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '生日'")
    private Date birthDate;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '所在党支部'")
    private String branchName;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '状态'")
    private String status;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '入党日期'")
    private Date partyDate;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '绑定日期'")
    private Date bindingDate;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '解绑日期'")
    private Date unbindingDate;

    @Column(nullable = true, columnDefinition = "int default 0 COMMENT '积分'")
    private int integral;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '工号'")
    private String jobNumber;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
