package com.bw.movie.my.mysound;

import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/30    8:18
 * author:Therefore(Lenovo)
 * fileName:MySoundService
 */
public interface MySoundService {
    @GET(UrlUtil.SYSMSG)
    Observable<MySoundUser> getSound(@Query("page") int page);
}
