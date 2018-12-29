package com.bw.movie.util;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * date:2018/12/26    8:34
 * author:Therefore(Lenovo)
 * fileName:ProjectApi
 */
public interface ProjectApi {

    @POST
    @FormUrlEncoded
    Call<String> login(@Url String url, @Field("phone") String phone, @Field("pwd") String pwd);


}
