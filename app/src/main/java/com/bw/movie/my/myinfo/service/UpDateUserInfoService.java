package com.bw.movie.my.myinfo.service;

import com.bw.movie.my.myinfo.bean.UpDateUserInfoEntity;
import com.bw.movie.util.UrlUtil;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/*
   修改用户信息的service接口
*/

public interface UpDateUserInfoService {

    @POST(UrlUtil.UPDATEUSER)
    @FormUrlEncoded
    Observable<UpDateUserInfoEntity> getUserInfo(@Field("nickName") String nickName, @Field("sex") int sex, @Field("email") String email);

}
