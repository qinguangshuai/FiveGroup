package com.bw.movie.wxapi.service;

import com.bw.movie.util.UrlUtil;
import com.bw.movie.wxapi.bean.OrderSuccessBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderSuccessService {
    @POST(UrlUtil.PAY)
    @FormUrlEncoded
    Observable<OrderSuccessBean> getOrderSuccess(@Field("payType") int payType, @Field("orderId") String orderId);
}
