package com.oee.pikachu.domain;

import lombok.Data;
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
@Table(name = "activity_info")
public class ActivityInfo extends Entitys implements Serializable {
    private static final long serialVersionUID = 7782609074694784755L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '活动名称'")
    private String activityName;

    @Column(nullable = true, columnDefinition = "varchar(64) COMMENT '活动地点'")
    private String activityAddr;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '開始日期'")
    private Date beginTime;

    @Column(nullable = true, columnDefinition = "datetime COMMENT '結束日期'")
    private Date endTime;

    @Column(nullable = true, columnDefinition = "int COMMENT '最大人数'")
    private Integer maxNumber;

    @Column(nullable = true, columnDefinition = "int COMMENT '参与人数'")
    private Integer joinNumber;

    @Column(nullable = true, columnDefinition = "varchar(64) COMMENT '状态'")
    private String status;

    @Column(nullable = true,length = 65535,columnDefinition="Text COMMENT '活动介绍'")
    private String activityMsg;

    @Column(nullable = true,length = 65535,columnDefinition="Text COMMENT '活动注意事项'")
    private String notes;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
