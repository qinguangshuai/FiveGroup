package com.bw.movie.cinema.search.service;

import com.bw.movie.cinema.search.bean.SearchBean;
import com.bw.movie.util.UrlUtil;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
/*
* SearchService
* */
public interface SearchService {
    @GET(UrlUtil.FINDALLCINEMA)
    Observable<SearchBean> getSearch(@Query("page") int page, @Query("count") int count, @Query("cinemaName") String cinemaName);
}
