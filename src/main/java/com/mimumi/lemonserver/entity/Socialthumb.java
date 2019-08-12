package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * socialthumb
 * @author 
 */
public class Socialthumb implements Serializable {
    /**
     * 点赞id
     */
    private Integer thumbid;

    /**
     * 被点赞的动态
     */
    private Integer socialid;

    /**
     * 点赞者
     */
    private Integer thumber;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getThumbid() {
        return thumbid;
    }

    public void setThumbid(Integer thumbid) {
        this.thumbid = thumbid;
    }

    public Integer getSocialid() {
        return socialid;
    }

    public void setSocialid(Integer socialid) {
        this.socialid = socialid;
    }

    public Integer getThumber() {
        return thumber;
    }

    public void setThumber(Integer thumber) {
        this.thumber = thumber;
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