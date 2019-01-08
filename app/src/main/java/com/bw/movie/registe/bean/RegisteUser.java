package com.bw.movie.registe.bean;

import com.bw.movie.base.BaseEntity;

/**
 * date:2018/12/26    18:39
 * author:Therefore(Lenovo)
 * fileName:RegisteUser
 */
public class RegisteUser extends BaseEntity {

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
