package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * social
 * @author 
 */
public class Social implements Serializable {
    /**
     * 动态id
     */
    private Integer socialid;

    /**
     * 发布者id
     */
    private Integer promulgatorid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    /**
     * 动态内容
     */
    private String content;

    private User user;

    private Integer isthumb;

    private List<Socialfile> pathlist;

    private static final long serialVersionUID = 1L;

    public List<Socialfile> getPathlist() {
        return pathlist;
    }

    public void setPathlist(List<Socialfile> pathlist) {
        this.pathlist = pathlist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIsthumb() {
        return isthumb;
    }

    public void setIsthumb(Integer isthumb) {
        this.isthumb = isthumb;
    }

    public Integer getSocialid() {
        return socialid;
    }

    public void setSocialid(Integer socialid) {
        this.socialid = socialid;
    }

    public Integer getPromulgatorid() {
        return promulgatorid;
    }

    public void setPromulgatorid(Integer promulgatorid) {
        this.promulgatorid = promulgatorid;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}