package com.bw.movie.login.bean;

import com.bw.movie.base.BaseEntity;

/**
 * date:2018/12/26    8:44
 * author:Therefore(Lenovo)
 * fileName:LoginUser
 */
public class XinUser extends BaseEntity {

    private String message;
    private String status;

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
