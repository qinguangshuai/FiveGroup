package com.bw.movie.my.mysound;

import com.bw.movie.base.BaseEntity;

/**
 * date:2018/12/30    10:43
 * author:Therefore(Lenovo)
 * fileName:UpdateSoundUser
 */
public class UpdateSoundUser extends BaseEntity {

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
