package com.bw.movie.my.ticket.service;

import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
*   购票信息service接口
* */
public interface TicketFormationService {
    @GET(UrlUtil.BUYRECORD)
    Observable<TicketFoemationEntity> getTicket(@Query("page") int page, @Query("count") int count);

}
