package com.bw.movie.my.myoption.bean;

import com.bw.movie.base.BaseEntity;

/*
  我的意见bean类
* */
public class MyOptionEntity extends BaseEntity {

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
