package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * invitecontact
 * @author 
 */
public class Invitecontact implements Serializable {
    /**
     * 邀请id
     */
    private Integer inviteid;

    /**
     * 邀请人id
     */
    private Integer inviter;

    /**
     * 被邀请人id
     */
    private Integer invitees;

    /**
     * 填写的邀请码
     */
    private String invitecode;

    private User inviterUser;

    private User inviteesUser;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public User getInviteesUser() {
        return inviteesUser;
    }

    public void setInviteesUser(User inviteesUser) {
        this.inviteesUser = inviteesUser;
    }

    public User getInviterUser() {
        return inviterUser;
    }

    public void setInviterUser(User inviterUser) {
        this.inviterUser = inviterUser;
    }

    public Integer getInviteid() {
        return inviteid;
    }

    public void setInviteid(Integer inviteid) {
        this.inviteid = inviteid;
    }

    public Integer getInviter() {
        return inviter;
    }

    public void setInviter(Integer inviter) {
        this.inviter = inviter;
    }

    public Integer getInvitees() {
        return invitees;
    }

    public void setInvitees(Integer invitees) {
        this.invitees = invitees;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
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