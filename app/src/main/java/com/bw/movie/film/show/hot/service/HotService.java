package com.bw.movie.film.show.hot.service;

import com.bw.movie.film.show.hot.bean.HotPlayBean;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface HotService {

     //请求正在热映数据 内网
     @GET(UrlUtil.RELEASEMOVIE)
     Observable<HotPlayBean> getHotPlayBeanObservable(@Query("page") int page, @Query("count") int count);
}
