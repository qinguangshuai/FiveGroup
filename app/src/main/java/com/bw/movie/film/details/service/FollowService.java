package com.bw.movie.film.details.service;

import com.bw.movie.film.details.bean.FollowBean;

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
    @GET("movie/v1/verify/followMovie")
    Observable<FollowBean> getFollowBeanObservable(@Query("movieId") int movieId);


}
