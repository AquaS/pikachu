package com.oee.pikachu.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/11/8.
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "activity_detail")
public class ActivityDetail extends Entitys implements Serializable {
    private static final long serialVersionUID = -7388612644889864248L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "bigint(20) COMMENT '活动id'")
    private Long activityId;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '工号'")
    private String jobNumber;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '真实姓名'")
    private String realName;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
