package com.bw.movie.cinema.good.service;

import com.bw.movie.cinema.good.bean.GoodBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 影院评论点赞
 */

public interface GoodService {
    @POST("cinema/v1/verify/cinemaCommentGreat")
    @FormUrlEncoded
    Observable<GoodBean> getGoods(@Field("commentId")int commentId);
}
