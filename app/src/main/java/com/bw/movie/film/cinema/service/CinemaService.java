package com.bw.movie.film.cinema.service;

import com.bw.movie.film.cinema.bean.CinemaBean;
import com.bw.movie.film.synopsis.bean.CommentBean;
import com.bw.movie.film.synopsis.bean.InputcommentsBean;
import com.bw.movie.film.synopsis.bean.PraiseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface CinemaService {



    //根据电影Id 查询影院
    @GET("movie/v1/findCinemasListByMovieId")
    Observable<CinemaBean> getCinemaBeanObservable(@Query("movieId") int movieId );





}
