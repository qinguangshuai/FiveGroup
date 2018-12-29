package com.bw.movie.cinema.mevaluate.service;

import com.bw.movie.cinema.mevaluate.bean.MevaluateBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/29    8:32
 * author:张文龙(张文龙)
 * fileName:MevaluateService
 */
public interface MevaluateService {
    @GET("cinema/v1/findAllCinemaComment")
    Observable<MevaluateBean> getMevaluate(@Query("cinemaId") int cinemaId, @Query("page") int page, @Query("count") int count);


}
