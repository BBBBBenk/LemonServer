package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * moth
 * @author 
 */
public class Moth implements Serializable {
    /**
     * 评价id
     */
    private Integer mothid;

    /**
     * 成交的需求id
     */
    private Integer applyid;

    /**
     * 评价人id
     */
    private Integer mother;

    /**
     * 评价信息
     */
    private String comment;

    /**
     * 真实度分数
     */
    private Integer real;

    /**
     * 服务态度分数
     */
    private Integer attitude;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getMothid() {
        return mothid;
    }

    public void setMothid(Integer mothid) {
        this.mothid = mothid;
    }

    public Integer getApplyid() {
        return applyid;
    }

    public void setApplyid(Integer applyid) {
        this.applyid = applyid;
    }

    public Integer getMother() {
        return mother;
    }

    public void setMother(Integer mother) {
        this.mother = mother;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getReal() {
        return real;
    }

    public void setReal(Integer real) {
        this.real = real;
    }

    public Integer getAttitude() {
        return attitude;
    }

    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}