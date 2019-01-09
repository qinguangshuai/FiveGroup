package com.bw.movie.film.show.playing.service;

import com.bw.movie.film.show.playing.playing.PlayingBean;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface PlayingService {

    //请求 正在上映 || 即将上映 数据  内网
    @GET(UrlUtil.COMINGSOONMOVIE)
    Observable<PlayingBean> getPlayingBeanObservable(@Query("page") int page, @Query("count") int count);

}
