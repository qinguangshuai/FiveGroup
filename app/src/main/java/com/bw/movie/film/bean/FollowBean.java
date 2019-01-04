package com.bw.movie.film.bean;

import java.io.Serializable;

/*
 *作者:ash
 *TODO:
 *
 */public class FollowBean implements Serializable {

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

