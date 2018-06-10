package com.oee.pikachu.domain;

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
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"workNum"})})
public class User extends Entitys implements Serializable {

    private static final long serialVersionUID = -9075774567020210970L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "varchar(32) COMMENT '微信ID'")
    private String wxid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '民族'")
    private String ethnicity;

    @Column(nullable = true, columnDefinition = "varchar(32) default '' COMMENT '学历'")
    private String degree;

//    @Column(nullable = true,length = 65535,columnDefinition="Text")
    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private String locatedBranch;

    @Column(nullable = false)
    private String company;

    @Column(nullable = true)
    private String partyDate;

    @Column(nullable = true)
    private int score;

    @Column(nullable = false, columnDefinition = "varchar(32) default '' COMMENT '工号'")
    private String workNum;

    @CreatedDate
    @Column(nullable = false)
    private Date createAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getLocatedBranch() {
        return locatedBranch;
    }

    public void setLocatedBranch(String locatedBranch) {
        this.locatedBranch = locatedBranch;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(String partyDate) {
        this.partyDate = partyDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", wxid='").append(wxid).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", ethnicity='").append(ethnicity).append('\'');
        sb.append(", degree='").append(degree).append('\'');
        sb.append(", birthDate='").append(birthDate).append('\'');
        sb.append(", locatedBranch='").append(locatedBranch).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", partyDate='").append(partyDate).append('\'');
        sb.append(", score=").append(score);
        sb.append(", workNum='").append(workNum).append('\'');
        sb.append(", createAt=").append(createAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
