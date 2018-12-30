package com.bw.movie.my.mysound;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/30    9:47
 * author:Therefore(Lenovo)
 * fileName:XiSoundService
 */
public interface UpdateSoundService {
    //http://172.17.8.100/movieApi/tool/v1/verify/changeSysMsgStatus
    @GET("tool/v1/verify/changeSysMsgStatus")
    Observable<UpdateSoundUser> getUpdate(@Query("id") int id);
}
