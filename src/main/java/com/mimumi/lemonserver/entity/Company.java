package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * company
 * @author 
 */
public class Company implements Serializable {
    /**
     * 公司id
     */
    private Integer companyid;

    /**
     * 公司名称
     */
    private String comname;

    /**
     * 经营范围
     */
    private String comscope;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 公司地址 定位地址
     */
    private String address;

    /**
     * 详细地址
     */
    private String location;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 首页logo
     */
    private String bannerimg;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getComscope() {
        return comscope;
    }

    public void setComscope(String comscope) {
        this.comscope = comscope;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getBannerimg() {
        return bannerimg;
    }

    public void setBannerimg(String bannerimg) {
        this.bannerimg = bannerimg;
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