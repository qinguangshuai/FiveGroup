package com.bw.movie.cinema.mevaluate.bean;

import com.bw.movie.base.BaseEntity;

import java.util.List;

/**
 * date:2018/12/29    8:31
 * author:张文龙(张文龙)
 * fileName:MevaluateBean
 */
public class MevaluateBean extends BaseEntity {

    private String message;
    private String status;
    private List<MevaResultBean> result;

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

    public List<MevaResultBean> getResult() {
        return result;
    }

    public void setResult(List<MevaResultBean> result) {
        this.result = result;
    }
}
