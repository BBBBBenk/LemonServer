package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * merchantfile
 * @author 
 */
public class Merchantfile implements Serializable {
    /**
     * 图片id
     */
    private Integer fileid;

    /**
     * 图片名称
     */
    private String filename;

    /**
     * 图片路径
     */
    private String path;

    /**
     * 文件类型 0-图片
     */
    private Integer filetype;

    /**
     * 入驻供应商id
     */
    private Integer merchantid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public Integer getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Integer merchantid) {
        this.merchantid = merchantid;
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