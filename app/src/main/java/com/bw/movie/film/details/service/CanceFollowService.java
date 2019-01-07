package com.bw.movie.film.details.service;

import com.bw.movie.film.details.bean.CancelFollowMovieBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface CanceFollowService {

    //取消关注
    @GET("movie/v1/verify/cancelFollowMovie")
    Observable<CancelFollowMovieBean> getCancelFollowMovieBeanObservable(@Query("movieId") int movieId);


}
