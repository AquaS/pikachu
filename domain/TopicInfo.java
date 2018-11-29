package com.oee.pikachu.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/11/18.
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "topic_info")
public class TopicInfo extends Entitys implements Serializable {
    private static final long serialVersionUID = -7864958350515958256L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '话题类型'")
    private String topicType;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '话题名称'")
    private String topicName;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '工号'")
    private String jobNumber;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '真实姓名'")
    private String realName;

    @Column(nullable = false, columnDefinition = "datetime COMMENT '发布时间'")
    private Date releaseTime;

    @Column(nullable = false, columnDefinition = "bigint(20) default 0 COMMENT '点赞人数'")
    private Long likeNo;

    @Column(nullable = false, columnDefinition = "bigint(20) default 0 COMMENT '评论人数'")
    private Long commentNo;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '话题状态'")
    private String status;

    @Column(nullable = false, columnDefinition = "text COMMENT '话题内容'")
    private String topicText;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
