package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * merchant
 * @author 
 */
public class Merchant implements Serializable {
    /**
     * 商家id
     */
    private Integer merchantid;

    /**
     * 公司名称
     */
    private String merchantname;

    /**
     * 经营范围
     */
    private String scope;

    /**
     * 发布者id
     */
    private Integer announcer;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 公司地址 定位地址
     */
    private String address;

    /**
     * 详细地址
     */
    private String location;

    /**
     * 联系人姓名
     */
    private String contact;

    /**
     * 联系人手机号
     */
    private String phone;

    /**
     * 列表展示的图片
     */
    private String bannerimg;

    //手机是否可见
    private Integer isphoneview;


    private List<Merchantthumb> merchantpic;

    private User user;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIsphoneview() {
        return isphoneview;
    }

    public void setIsphoneview(Integer isphoneview) {
        this.isphoneview = isphoneview;
    }

    public List<Merchantthumb> getMerchantpic() {
        return merchantpic;
    }

    public void setMerchantpic(List<Merchantthumb> merchantpic) {
        this.merchantpic = merchantpic;
    }

    public Integer getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Integer merchantid) {
        this.merchantid = merchantid;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getAnnouncer() {
        return announcer;
    }

    public void setAnnouncer(Integer announcer) {
        this.announcer = announcer;
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