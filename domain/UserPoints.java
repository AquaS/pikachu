package com.oee.pikachu.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Aqua on 2018/6/10.
 */
@Entity
@Table(name = "user_points", uniqueConstraints = {@UniqueConstraint(columnNames = {"wxid", "date"})})
public class UserPoints extends Entitys implements Serializable {
    private static final long serialVersionUID = 1056186659135793327L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '微信id'")
    private String wxid;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getConsumePoints() {
        return consumePoints;
    }

    public void setConsumePoints(int consumePoints) {
        this.consumePoints = consumePoints;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserPoints{");
        sb.append("id=").append(id);
        sb.append(", wxid='").append(wxid).append('\'');
        sb.append(", date=").append(date);
        sb.append(", points=").append(points);
        sb.append(", consumePoints=").append(consumePoints);
        sb.append(", state=").append(state);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
