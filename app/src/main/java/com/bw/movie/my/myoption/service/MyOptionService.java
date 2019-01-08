package com.bw.movie.my.myoption.service;
import com.bw.movie.my.myoption.bean.MyOptionEntity;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/*
  我的意见service接口
* */
public interface MyOptionService {
    //我的意见
    @POST(UrlUtil.MYRECORD)
    @FormUrlEncoded
    Observable<MyOptionEntity> getOption(@Field("content") String content);
}
