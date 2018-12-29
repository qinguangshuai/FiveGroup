package com.bw.movie.wxapi.service;

import com.bw.movie.wxapi.bean.WXUser;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * date:2018/12/27    9:50
 * author:Therefore(Lenovo)
 * fileName:WXService
 */
public interface WXService {
    //http://172.17.8.100/movieApi/user/v1/weChatBindingLogin?code=0016X1X91bUUWO1JydV91PSRW916X1Xl
    @POST("user/v1/weChatBindingLogin")
    @FormUrlEncoded
    Observable<WXUser> postWX(@Field("code") String code);
}
