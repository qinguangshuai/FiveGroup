package com.bw.movie.my.mysound;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/30    8:18
 * author:Therefore(Lenovo)
 * fileName:MySoundService
 */
public interface MySoundService {
    //http://172.17.8.100/movieApi/tool/v1/verify/findAllSysMsgList?page=1&count=5
    @GET("tool/v1/verify/findAllSysMsgList?count=50")
    Observable<MySoundUser> getSound(@Query("page") int page);
}
