package com.bw.movie.film.synopsis.service;

import com.bw.movie.film.synopsis.bean.CommentBean;
import com.bw.movie.film.synopsis.bean.InputcommentsBean;
import com.bw.movie.film.synopsis.bean.PraiseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface SynopsisService {

    //请求评论接口
    @GET("movie/v1/findAllMovieComment")
    Observable<CommentBean> getCommentBeanObservable(@Query("movieId") int movieId, @Query("page") int page, @Query("count") int count);

    //评论回复
    @POST("movie/v1/verify/commentReply")
    @FormUrlEncoded
    Observable<InputcommentsBean> getInputcomments(@Field("commentId") int commentId, @Field("replyContent") String replyContent);


    //点赞
    @POST("movie/v1/verify/movieCommentGreat")
    @FormUrlEncoded
    Observable<PraiseBean> getPraiseBeanObservable(@Field("commentId") int commentId);

}
