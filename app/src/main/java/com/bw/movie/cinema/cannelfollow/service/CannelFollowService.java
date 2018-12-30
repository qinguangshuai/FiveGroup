package com.bw.movie.cinema.cannelfollow.service;

import com.bw.movie.cinema.follow.bean.FollowBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/27    17:59
 * author:张文龙(张文龙)
 * fileName:CannelFollowService
 */
public interface CannelFollowService {



    @GET("cinema/v1/verify/cancelFollowCinema")
    Observable<FollowBean> getCannelFollow(@Query("cinemaId") int cinemaId);
}
