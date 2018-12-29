package com.bw.movie.my.message.service;



import com.bw.movie.my.message.bean.MyMessageEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;

/*
  MyMessageService
* */
public interface MyMessageService {

    @GET("user/v1/verify/getUserInfoByUserId")
    Observable<MyMessageEntity> getMessage();

}
