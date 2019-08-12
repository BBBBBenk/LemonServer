package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * accountlog
 * @author 
 */
public class AccountLog implements Serializable {
    /**
     * 日志id
     */
    private Integer logid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 等级积分
     */
    private BigDecimal rankpoints;

    /**
     * 消费
     */
    private BigDecimal paypoints;

    /**
     * 操作时间
     */
    private Date changetime;

    /**
     * 操作备注
     */
    private String changedesc;

    /**
     * 操作类型 0-充值 1-提现
     */
    private Integer changetype;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public BigDecimal getRankpoints() {
        return rankpoints;
    }

    public void setRankpoints(BigDecimal rankpoints) {
        this.rankpoints = rankpoints;
    }

    public BigDecimal getPaypoints() {
        return paypoints;
    }

    public void setPaypoints(BigDecimal paypoints) {
        this.paypoints = paypoints;
    }

    public Date getChangetime() {
        return changetime;
    }

    public void setChangetime(Date changetime) {
        this.changetime = changetime;
    }

    public String getChangedesc() {
        return changedesc;
    }

    public void setChangedesc(String changedesc) {
        this.changedesc = changedesc;
    }

    public Integer getChangetype() {
        return changetype;
    }

    public void setChangetype(Integer changetype) {
        this.changetype = changetype;
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