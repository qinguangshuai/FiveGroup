package com.bw.movie.film.cinema.bean;

import com.bw.movie.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/*
 *作者:ash
 *TODO:
 *
 */
public class CinemaBean extends BaseEntity {

    private String message;
    private String status;
    private List<CinemaResultBean> result;

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

    public List<CinemaResultBean> getResult() {
        return result;
    }

    public void setResult(List<CinemaResultBean> result) {
        this.result = result;
    }
}
