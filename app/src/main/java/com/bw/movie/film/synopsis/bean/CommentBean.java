package com.bw.movie.film.synopsis.bean;

import java.io.Serializable;
import java.util.List;

/*
 *作者:ash
 *TODO:
 *
 */
public class CommentBean implements Serializable {

    private String message;
    private String status;
    private List<CommentResultBean> result;

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

    public List<CommentResultBean> getResult() {
        return result;
    }

    public void setResult(List<CommentResultBean> result) {
        this.result = result;
    }
}
