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
@Table(name = "topic_type_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"topicType"})})
public class TopicTypeInfo extends Entitys implements Serializable {
    private static final long serialVersionUID = 6557792635453069139L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '话题类型'")
    private String topicType;

    @CreatedDate
    @Column(nullable = false)
    private Date insertTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
