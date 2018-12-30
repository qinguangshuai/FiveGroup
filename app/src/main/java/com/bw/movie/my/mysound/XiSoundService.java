package com.bw.movie.my.mysound;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * date:2018/12/30    9:47
 * author:Therefore(Lenovo)
 * fileName:XiSoundService
 */
public interface XiSoundService {
    //http://172.17.8.100/movieApi/tool/v1/verify/findUnreadMessageCount
    @GET("tool/v1/verify/findUnreadMessageCount")
    Observable<XiSoundUser> getXi();
}
