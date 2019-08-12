package com.mimumi.lemonserver.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ResponseResult implements Serializable {

    private Integer  status ;

    private String  message ;

    private Object  data ;

    private String others ;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
