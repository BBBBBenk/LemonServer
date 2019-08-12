package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * recharge
 * @author 
 */
public class Recharge implements Serializable {
    /**
     * 充值id
     */
    private Integer rechargeid;

    /**
     * 充值目标id
     */
    private Integer target;

    /**
     * 操作人id
     */
    private Integer operator;

    /**
     * 充值额度
     */
    private Long amount;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getRechargeid() {
        return rechargeid;
    }

    public void setRechargeid(Integer rechargeid) {
        this.rechargeid = rechargeid;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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