package com.mimumi.lemonserver.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * users
 * @author 
 */
public class User implements Serializable {
    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 地址
     */
    private String address;

    /**
     * 头像
     */
    private String headimgurl;

    /**
     * 用户类型 0-普通用户  1-客服   9-管理员
     */
    private Integer usertype;

    /**
     * 是否会员 0-否 1-是
     */
    private Integer isvip;

    /**
     * 生效日期
     */
    private Date effectdate;

    /**
     * 失效日期
     */
    private Date effectdateto;

    /**
     * 积分
     */
    private BigDecimal totalpoints;

    /**
     * 等级id
     */
    private Integer rankid;

    /**
     * 等级
     */
    private UserRank userrank;


    /**
     * 注册时间
     */
    private Date regtime;

    /**
     * 是否黑名单 0-否 1-是
     */
    private Integer isbacklist;

    /**
     * 微信openid
     */
    private String openid;

    private int m_phone_view;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    private String code;

    private static final long serialVersionUID = 1L;

    public int getM_phone_view() {
        return m_phone_view;
    }

    public void setM_phone_view(int m_phone_view) {
        this.m_phone_view = m_phone_view;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getIsvip() {
        return isvip;
    }

    public void setIsvip(Integer isvip) {
        this.isvip = isvip;
    }

    public Date getEffectdate() {
        return effectdate;
    }

    public void setEffectdate(Date effectdate) {
        this.effectdate = effectdate;
    }

    public Date getEffectdateto() {
        return effectdateto;
    }

    public void setEffectdateto(Date effectdateto) {
        this.effectdateto = effectdateto;
    }

    public BigDecimal getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(BigDecimal totalpoints) {
        this.totalpoints = totalpoints;
    }

    public Integer getRankid() {
        return rankid;
    }

    public void setRankid(Integer rankid) {
        this.rankid = rankid;
    }

    public UserRank getUserrank() {
        return userrank;
    }

    public void setUserrank(UserRank userrank) {
        this.userrank = userrank;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Integer getIsbacklist() {
        return isbacklist;
    }

    public void setIsbacklist(Integer isbacklist) {
        this.isbacklist = isbacklist;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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