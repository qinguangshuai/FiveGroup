package com.bw.movie.cinema.follow.bean;

import com.bw.movie.base.BaseEntity;

/**
 * date:2018/12/27    17:29
 * author:张文龙(张文龙)
 * fileName:FollowBean
 */
public class FollowBean extends BaseEntity {

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
