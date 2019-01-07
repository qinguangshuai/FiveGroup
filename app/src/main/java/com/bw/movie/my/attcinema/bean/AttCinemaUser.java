package com.bw.movie.my.attcinema.bean;

import java.util.List;

/**
 * date:2018/12/28    15:09
 * author:Therefore(Lenovo)
 * fileName:AttCinemaUser
 */
public class AttCinemaUser {

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }


}
