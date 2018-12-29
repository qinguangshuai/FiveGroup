package com.bw.movie.my.ticket.service;

import com.bw.movie.my.ticket.bean.TicketFoemationEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;

/*
*   购票信息service接口
* */
public interface TicketFormationService {
    @GET("user/v1/verify/findUserBuyTicketRecordList")
    Observable<TicketFoemationEntity> getTicket(@Field("page") int page, @Field("count") int count);

}
