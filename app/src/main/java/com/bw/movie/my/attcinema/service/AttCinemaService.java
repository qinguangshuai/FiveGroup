package com.bw.movie.my.attcinema.service;

import com.bw.movie.my.attcinema.bean.AttCinemaUser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/28    15:11
 * author:Therefore(Lenovo)
 * fileName:AttCinemaService
 */
public interface AttCinemaService {
    @GET("cinema/v1/verify/findCinemaPageList?count=10")
    Observable<AttCinemaUser> getCinema(@Query("page") int page);
}
