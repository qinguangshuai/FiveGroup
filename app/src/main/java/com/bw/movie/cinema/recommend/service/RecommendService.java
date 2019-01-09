package com.bw.movie.cinema.recommend.service;

import com.bw.movie.cinema.recommend.bean.RecommendBean;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecommendService {
    @GET(UrlUtil.RECOMMEN)
    Observable<RecommendBean> getRecommned(@Query("longitude") String longitude,
                                           @Query("latitude") String latitude,
                                           @Query("page") int page,
                                           @Query("count") int count);

}
