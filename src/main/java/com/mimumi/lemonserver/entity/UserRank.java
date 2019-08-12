package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * userrank
 * @author 
 */
public class UserRank implements Serializable {
    /**
     * 等级id
     */
    private Integer rankid;

    /**
     * 等级编码 0-按点击次数 1-按月
     */
    private Integer rankcode;

    /**
     * 等级名称
     */
    private String rankname;

    /**
     * 一次消费积分
     */
    private Integer payonce;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getRankid() {
        return rankid;
    }

    public void setRankid(Integer rankid) {
        this.rankid = rankid;
    }

    public Integer getRankcode() {
        return rankcode;
    }

    public void setRankcode(Integer rankcode) {
        this.rankcode = rankcode;
    }

    public String getRankname() {
        return rankname;
    }

    public void setRankname(String rankname) {
        this.rankname = rankname;
    }

    public Integer getPayonce() {
        return payonce;
    }

    public void setPayonce(Integer payonce) {
        this.payonce = payonce;
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