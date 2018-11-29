package com.oee.pikachu.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/11/19.
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "topic_like_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"topicId", "jobNumber"})})
public class TopicLikeInfo extends Entitys implements Serializable {
    private static final long serialVersionUID = 1960217358218229557L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "bigint(20) COMMENT '话题ID'")
    private Long topicId;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '工号'")
    private String jobNumber;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '真实姓名'")
    private String realName;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
