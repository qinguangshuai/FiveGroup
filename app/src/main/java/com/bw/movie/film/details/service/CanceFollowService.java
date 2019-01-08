package com.bw.movie.film.details.service;

import com.bw.movie.film.details.bean.CancelFollowMovieBean;
import com.bw.movie.util.UrlUtil;

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
    @GET(UrlUtil.CANCELFOLLOWMOVIE)
    Observable<CancelFollowMovieBean> getCancelFollowMovieBeanObservable(@Query("movieId") int movieId);

}
