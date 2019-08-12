package com.mimumi.lemonserver.dto;

/**
 * Created by Administrator on 2017/2/10.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 分页器
 *
 * @author Herbert
 *
 * @param <T>
 */

public class PageVo<T> {


    /**
     * 总数
     */
    private long total;


    /**
     * 数据
     */
    private List<T> rows;


    public PageVo(long total) {

        this.total= total;
        this.rows=new ArrayList<T>();
    }

    public long getTotal(){return total;}

    public void setTotal(long total) {this.total=total;}

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


}