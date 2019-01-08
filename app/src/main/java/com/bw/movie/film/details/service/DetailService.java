package com.bw.movie.film.details.service;

import com.bw.movie.film.details.bean.DetailBean;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface DetailService {

    //通过ID 查找 电影详情页面
    @GET(UrlUtil.FINDMOVIESDETAIL)
    Observable<DetailBean> getDetailBeanObservable(@Query("movieId") int movieId);
}
