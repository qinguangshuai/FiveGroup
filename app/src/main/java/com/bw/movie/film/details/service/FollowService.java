package com.bw.movie.film.details.service;

import com.bw.movie.film.details.bean.FollowBean;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface FollowService {
    //关注电影
    @GET(UrlUtil.FOLLOWMOVIE)
    Observable<FollowBean> getFollowBeanObservable(@Query("movieId") int movieId);
}
