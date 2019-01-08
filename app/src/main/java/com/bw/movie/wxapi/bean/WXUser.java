package com.bw.movie.wxapi.bean;

import com.bw.movie.base.BaseEntity;

/**
 * date:2018/12/27    9:49
 * author:Therefore(Lenovo)
 * fileName:WXUser
 */
public class WXUser extends BaseEntity {

    private WXResultBean result;
    private String message;
    private String status;

    public WXResultBean getResult() {
        return result;
    }

    public void setResult(WXResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
