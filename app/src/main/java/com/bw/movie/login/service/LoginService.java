package com.bw.movie.login.service;

import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.registe.bean.RegisteUser;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    //登录
    @POST(UrlUtil.LOGIN)
    @FormUrlEncoded
    Observable<LoginUser> getLogn(@Field("phone") String phone, @Field("pwd") String pwd);

}
