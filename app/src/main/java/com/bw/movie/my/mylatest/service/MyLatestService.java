package com.bw.movie.my.mylatest.service;

import com.bw.movie.my.mylatest.bean.MyLatestUser;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * date:2018/12/27    19:37
 * author:Therefore(Lenovo)
 * fileName:MyLatestService
 */
public interface MyLatestService {
    //http://172.17.8.100/movieApi/tool/v1/findNewVersion
    @GET("tool/v1/findNewVersion")
    Observable<MyLatestUser> getVersion();
}
