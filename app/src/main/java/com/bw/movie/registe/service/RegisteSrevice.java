package com.bw.movie.registe.service;

import com.bw.movie.registe.bean.RegisteUser;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * date:2018/12/26    18:45
 * author:Therefore(Lenovo)
 * fileName:RegisteSrevice
 */
public interface RegisteSrevice {
    //注册http://172.17.8.100/movieApi/user/v1/registerUser
    @POST("user/v1/registerUser")
    @FormUrlEncoded
    Observable<RegisteUser> postRegiste(@Field("nickName") String nickName, @Field("phone") String phone, @Field("pwd") String pwd, @Field("pwd2") String pwd2, @Field("sex") int sex, @Field("birthday") String birthday, @Field("imei") String imei, @Field("ua") String ua, @Field("screenSize") String screenSize, @Field("os") String os, @Field("email") String email);
}
