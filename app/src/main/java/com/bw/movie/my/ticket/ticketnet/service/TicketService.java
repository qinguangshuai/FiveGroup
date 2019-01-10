package com.bw.movie.my.ticket.ticketnet.service;

import com.bw.movie.my.ticket.ticketnet.bean.TicketBean;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TicketService {
    @POST(UrlUtil.SHOPPAY)
    @FormUrlEncoded
    Observable<TicketBean> getTicket(@Field("scheduleId") int scheduleId, @Field("amount") int amount,
                                     @Field("sign") String sign);
}
