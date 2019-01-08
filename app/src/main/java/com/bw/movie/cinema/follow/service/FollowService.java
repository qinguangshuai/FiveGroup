package com.bw.movie.cinema.follow.service;

import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/27    17:29
 * author:张文龙(张文龙)
 * fileName:FollowService
 */
public interface FollowService {
    @GET(UrlUtil.YFOLLOWCINEMA)
    Observable<FollowBean> getFollow(@Query("cinemaId") int cinemaId);
}
