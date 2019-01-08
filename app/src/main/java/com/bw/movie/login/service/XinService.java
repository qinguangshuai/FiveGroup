package com.bw.movie.login.service;

import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.login.bean.XinUser;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface XinService {

    @POST("tool/v1/verify/uploadPushToken")
    @FormUrlEncoded
    Observable<XinUser> getXinGe(@Field("token") String token, @Field("os") int os);

}
