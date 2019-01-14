package com.bw.movie.my.myinfo.updatepwd.bean;

import com.bw.movie.base.BaseEntity;

/*
   修改密码bean类
* */
public class UpdatePwdEntity extends BaseEntity {


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
