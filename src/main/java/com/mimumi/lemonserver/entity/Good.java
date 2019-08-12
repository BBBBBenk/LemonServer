package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * goods
 * @author 
 */
public class Good implements Serializable {
    /**
     * 商品id
     */
    private Integer goodid;

    /**
     * 商品类型
     */
    private String goodtype;

    /**
     * 商品名称
     */
    private String goodname;

    /**
     * 价格
     */
    private BigDecimal marketprice;

    /**
     * 联系方式 0-电话号码 1-留言模式
     */
    private Integer contractmethod;

    /**
     * 点击量
     */
    private Integer clickcount;

    /**
     * vip区 0-否 1-是
     */
    private Integer isvip;

    /**
     * 发布人id
     */
    private Integer publisherid;

    /**
     * 发布时间
     */
    private Date publishtime;


    /**
     * 已回收
     */
    private Boolean isrecovery;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;


    /**
     * 商品备注
     */
    private String comment;

    private User user;

    private Integer goodnum;

    private String contact;

    private String phone;

    private String address;

    private String standard;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGoodnum() {
        return goodnum;
    }

    public void setGoodnum(Integer goodnum) {
        this.goodnum = goodnum;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    /**
     * 商品图片
     */
    private List<GoodFile>  goodPictureList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private static final long serialVersionUID = 1L;

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public String getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public BigDecimal getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }

    public Integer getContractmethod() {
        return contractmethod;
    }

    public void setContractmethod(Integer contractmethod) {
        this.contractmethod = contractmethod;
    }

    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    public Integer getIsvip() {
        return isvip;
    }

    public void setIsvip(Integer isvip) {
        this.isvip = isvip;
    }

    public Integer getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Boolean getIsrecovery() {
        return isrecovery;
    }

    public void setIsrecovery(Boolean isrecovery) {
        this.isrecovery = isrecovery;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<GoodFile> getGoodPictureList() {
        return goodPictureList;
    }

    public void setGoodPictureList(List<GoodFile> goodPictureList) {
        this.goodPictureList = goodPictureList;
    }
}