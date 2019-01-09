package com.bw.movie.film.show.popular.service;

import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface PopularService {
    //请求热门电影数据  内网
    @GET(UrlUtil.HOTMOVIE)
    Observable<PopularBean> getPopularBeanObservable(@Query("page") int page, @Query("count") int count);

}
