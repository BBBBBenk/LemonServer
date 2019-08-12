package com.mimumi.lemonserver.condition;

import java.util.List;

/**
 * Created by Administrator on 2018/8/15.
 */
public class GoodCondition {


    /**
     * vip区 0-否 1-是
     */
    private Integer isvip;

    /**
     * 关键字
     */
    private String keyword;


    public Integer getIsvip() {
        return isvip;
    }

    public void setIsvip(Integer isvip) {
        this.isvip = isvip;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


}
