package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * viporder
 * @author 
 */
public class Viporder implements Serializable {
    /**
     * Vip订单id
     */
    private Integer vorderid;

    /**
     * 统一下单订单号
     */
    private String outtradeno;

    /**
     * 0-下单 1-已支付
     */
    private Integer stauts;

    /**
     * 开通用户
     */
    private Integer userid;

    /**
     * 订单金额
     */
    private Long amount;

    /**
     * vip类型
     */
    private Integer viptype;

    /**
     * 支付时间
     */
    private Date paytime;

    /**
     * 创建时间
     */
    private Date createtime;

    private Integer goodid;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public Integer getVorderid() {
        return vorderid;
    }

    public void setVorderid(Integer vorderid) {
        this.vorderid = vorderid;
    }

    public String getOuttradeno() {
        return outtradeno;
    }

    public void setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno;
    }

    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getViptype() {
        return viptype;
    }

    public void setViptype(Integer viptype) {
        this.viptype = viptype;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
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