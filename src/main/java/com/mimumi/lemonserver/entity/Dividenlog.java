package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * dividenlog
 * @author 
 */
public class Dividenlog implements Serializable {
    /**
     * 分成记录id
     */
    private Integer dividenid;

    /**
     * 消费者id
     */
    private Integer consumer;

    /**
     * 获利者id
     */
    private Integer dividener;

    /**
     * 分成额度
     */
    private BigDecimal amount;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getDividenid() {
        return dividenid;
    }

    public void setDividenid(Integer dividenid) {
        this.dividenid = dividenid;
    }

    public Integer getConsumer() {
        return consumer;
    }

    public void setConsumer(Integer consumer) {
        this.consumer = consumer;
    }

    public Integer getDividener() {
        return dividener;
    }

    public void setDividener(Integer dividener) {
        this.dividener = dividener;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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