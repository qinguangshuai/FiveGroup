package com.bw.movie.film.cinema.service;

import com.bw.movie.film.cinema.bean.CinemaBean;
import com.bw.movie.util.UrlUtil;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface CinemaService {
    //根据电影Id 查询影院
    @GET(UrlUtil.CINEMAIDFINDCINEMA)
    Observable<CinemaBean> getCinemaBeanObservable(@Query("movieId") int movieId );
}
