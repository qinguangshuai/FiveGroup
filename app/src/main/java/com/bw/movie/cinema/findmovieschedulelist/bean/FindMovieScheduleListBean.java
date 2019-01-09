package com.bw.movie.cinema.findmovieschedulelist.bean;

import com.bw.movie.base.BaseEntity;

import java.util.List;

/**
 * date:2018/12/27    18:59
 * author:张文龙(张文龙)
 * fileName:FindMovieScheduleListBean
 */
public class FindMovieScheduleListBean extends BaseEntity {

    private String message;
    private String status;
    private List<FindResultBean> result;

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

    public List<FindResultBean> getResult() {
        return result;
    }

    public void setResult(List<FindResultBean> result) {
        this.result = result;
    }

}
