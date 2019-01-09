package com.bw.movie.cinema.mdetails.bean;

import com.bw.movie.base.BaseEntity;

/**
 * date:2018/12/28    18:34
 * author:张文龙(张文龙)
 * fileName:MdetailsBean
 */
public class MdetailsBean  extends BaseEntity {

    private MdetailsResultBean result;
    private String message;
    private String status;

    public MdetailsResultBean getResult() {
        return result;
    }

    public void setResult(MdetailsResultBean result) {
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
