package com.bw.movie.cinema.Particulars.bean;

import com.bw.movie.base.BaseEntity;

import java.util.List;

/**
 * date:2018/12/27    16:11
 * author:张文龙(张文龙)
 * fileName:MovieListByCinemaIdBean
 */
public class MovieListByCinemaIdBean  extends BaseEntity {




    private String message;
    private String status;
    private List<MovieResultBean> result;

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

    public List<MovieResultBean> getResult() {
        return result;
    }

    public void setResult(List<MovieResultBean> result) {
        this.result = result;
    }
}
