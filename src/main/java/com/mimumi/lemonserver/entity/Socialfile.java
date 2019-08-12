package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * socialfile
 * @author 
 */
public class Socialfile implements Serializable {
    /**
     * 文件id
     */
    private Integer fileid;

    /**
     * 动态id
     */
    private Integer socialid;

    /**
     * 创建时间/上传时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    /**
     * 文件路径
     */
    private String filepath;

    private static final long serialVersionUID = 1L;

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Integer getSocialid() {
        return socialid;
    }

    public void setSocialid(Integer socialid) {
        this.socialid = socialid;
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

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}