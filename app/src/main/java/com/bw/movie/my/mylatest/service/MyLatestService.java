package com.bw.movie.my.mylatest.service;

import com.bw.movie.my.mylatest.bean.MyLatestUser;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * date:2018/12/27    19:37
 * author:Therefore(Lenovo)
 * fileName:MyLatestService
 */
public interface MyLatestService {
    @GET(UrlUtil.NEWVERSION)
    Observable<MyLatestUser> getVersion();
}
