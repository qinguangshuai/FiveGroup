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
    @GET("tool/v1/verify/changeSysMsgStatus")
    Observable<UpdateSoundUser> getUpdate(@Query("id") int id);
}
