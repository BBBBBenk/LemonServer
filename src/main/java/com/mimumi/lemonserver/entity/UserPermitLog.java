package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * userpermitlog
 * @author 
 */
public class UserPermitLog implements Serializable {
    /**
     * 日志id
     */
    private Integer logid;

    /**
     * 商品id
     */
    private Integer goodid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 允许查看 0-否 1-是
     */
    private Integer permitview;

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

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPermitview() {
        return permitview;
    }

    public void setPermitview(Integer permitview) {
        this.permitview = permitview;
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