package com.bw.movie.cinema.bean.neightbourbean;


import java.io.Serializable;
import java.util.List;

public class NeightbourBean implements Serializable {

    private NeightResultBean result;
    private String message;
    private String status;

    public NeightResultBean getResult() {
        return result;
    }

    public void setResult(NeightResultBean result) {
        this.result = result;
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
