package com.bw.movie.my.myinfo.updatepwd.service;



import com.bw.movie.my.myinfo.updatepwd.bean.UpdatePwdEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/*
   修改密码service接口
* */
public interface UpdatePwdService {
    @POST("user/v1/verify/modifyUserPwd")
    @FormUrlEncoded
    Observable<UpdatePwdEntity> getPwd(@Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd, @Field("newPwd2") String newPwd2);

}
