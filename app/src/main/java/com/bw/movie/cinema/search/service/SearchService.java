package com.bw.movie.cinema.search.service;

import com.bw.movie.cinema.search.bean.SearchBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("cinema/v1/findAllCinemas")
    Observable<SearchBean> getSearch(@Query("page") int page, @Query("count") int count, @Query("cinemaName") String cinemaName);
}
