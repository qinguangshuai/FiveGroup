package com.bw.movie.my.mysound;

import com.bw.movie.base.BaseEntity;

/**
 * date:2018/12/30    9:47
 * author:Therefore(Lenovo)
 * fileName:XiSoundUser
 */
public class XiSoundUser extends BaseEntity {

    private int count;
    private String message;
    private String status;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
