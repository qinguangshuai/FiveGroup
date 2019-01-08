package com.bw.movie.cinema.Particulars;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *影院内容轮播接口
 */
public interface ParticularsService {
    @GET("movie/v1/findHotMovieList")
    Observable<ParticularsBean> getParticulars(@Query("page") int page, @Query("count") int count);
}
