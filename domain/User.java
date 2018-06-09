package com.oee.pikachu.domain;

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
public class User extends Entitys implements Serializable {

    private static final long serialVersionUID = -9075774567020210970L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String wxid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String ethnicity;
    @Column(nullable = true)
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
    @Column(nullable = true)
    private Date createAt;
    @Column(nullable = true)
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
