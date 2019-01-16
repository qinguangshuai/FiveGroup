package com.bw.movie.cinema.bean.neightbourbean;


import java.io.Serializable;
import java.util.List;

public class NeightbourBean implements Serializable {

    private String message;
    private String status;
    private List<NeightBourResultBean> result;

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

    public List<NeightBourResultBean> getResult() {
        return result;
    }

    public void setResult(List<NeightBourResultBean> result) {
        this.result = result;
    }
}
