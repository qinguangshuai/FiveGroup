package com.bw.movie.film.bean;

/**
 * 添加用户对评论的回复<bean>
 */
public class InputcommentsBean {
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
