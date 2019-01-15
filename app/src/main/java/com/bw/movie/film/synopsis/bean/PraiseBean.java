package com.bw.movie.film.synopsis.bean;

import com.bw.movie.base.BaseEntity;


/*
 *作者:ash
 *TODO:
 *
 */
public class PraiseBean extends BaseEntity {
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
