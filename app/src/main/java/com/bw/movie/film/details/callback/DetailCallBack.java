package com.bw.movie.film.details.callback;

import com.bw.movie.film.details.bean.DetailBean;

/*
 *作者:ash
 *TODO:
 *   详情 回调信息
 */
public interface DetailCallBack {
    void success(DetailBean detailBean);
    void error(String message);
}
