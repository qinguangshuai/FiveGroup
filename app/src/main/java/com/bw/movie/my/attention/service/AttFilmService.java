package com.bw.movie.my.attention.service;

import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attention.bean.MyAttFilmUser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/28    15:11
 * author:Therefore(Lenovo)
 * fileName:AttCinemaService
 */
public interface AttFilmService {
    @GET("movie/v1/verify/findMoviePageList?count=5")
    Observable<MyAttFilmUser> getFilm(@Query("page") int page);
}
