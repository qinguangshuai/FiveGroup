package com.bw.movie.my.message.service;



import com.bw.movie.my.message.bean.MyMessageEntity;
import com.bw.movie.util.UrlUtil;
import io.reactivex.Observable;
import retrofit2.http.GET;

/*
  MyMessageService
* */
public interface MyMessageService {

    @GET(UrlUtil.USERINFOBYID)
    Observable<MyMessageEntity> getMessage();

}
