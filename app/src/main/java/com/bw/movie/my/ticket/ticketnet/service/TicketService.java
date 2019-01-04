package com.bw.movie.my.ticket.ticketnet.service;

import com.bw.movie.my.ticket.ticketnet.bean.TicketBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TicketService {
    @POST("movie/v1/verify/buyMovieTicket")
    @FormUrlEncoded
    Observable<TicketBean> getTicket(@Field("scheduleId") int scheduleId, @Field("amount") int amount,
                                     @Field("sign") String sign);
}
