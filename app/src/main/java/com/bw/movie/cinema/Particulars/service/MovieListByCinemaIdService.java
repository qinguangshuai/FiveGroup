package com.bw.movie.cinema.Particulars.service;

import com.bw.movie.cinema.Particulars.bean.MovieListByCinemaIdBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/27    16:10
 * author:张文龙(张文龙)
 * fileName:MovieListByCinemaIdService
 */
public interface MovieListByCinemaIdService {


    @GET("movie/v1/findMovieListByCinemaId")
    Observable<MovieListByCinemaIdBean> getMovieByBean(@Query("cinemaId") int cinemaId);
}
