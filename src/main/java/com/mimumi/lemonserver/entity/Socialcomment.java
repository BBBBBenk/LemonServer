package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * socialcomment
 * @author 
 */
public class Socialcomment implements Serializable {
    /**
     * 评论id
     */
    private Integer commentid;

    /**
     * 动态id
     */
    private Integer socialid;

    /**
     * 评论用户id
     */
    private Integer fromuid;

    /**
     * 评论目标用户id
     */
    private Integer touid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private User receiver;

    private User sender;

    private static final long serialVersionUID = 1L;

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getSocialid() {
        return socialid;
    }

    public void setSocialid(Integer socialid) {
        this.socialid = socialid;
    }

    public Integer getFromuid() {
        return fromuid;
    }

    public void setFromuid(Integer fromuid) {
        this.fromuid = fromuid;
    }

    public Integer getTouid() {
        return touid;
    }

    public void setTouid(Integer touid) {
        this.touid = touid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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