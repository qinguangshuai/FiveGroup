package com.bw.movie.cinema.findmovieschedulelist.service;

import com.bw.movie.cinema.findmovieschedulelist.bean.FindMovieScheduleListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/27    18:59
 * author:张文龙(张文龙)
 * fileName:FindMovieScheduleListService
 */
public interface  FindMovieScheduleListService {


    @GET("movie/v1/findMovieScheduleList")
    Observable<FindMovieScheduleListBean> getFindMovieScheduleList(@Query("cinemasId") int cinemasId,
                                                                   @Query("movieId") int movieId);
}
