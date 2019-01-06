package com.bw.movie.film.service;

import com.bw.movie.film.bean.CancelFollowMovieBean;
import com.bw.movie.film.bean.CarouselBean;
import com.bw.movie.film.bean.CinemaBean;
import com.bw.movie.film.bean.CommentBean;
import com.bw.movie.film.bean.DetailBean;
import com.bw.movie.film.bean.FollowBean;
import com.bw.movie.film.bean.HotPlayBean;
import com.bw.movie.film.bean.InputcommentsBean;
import com.bw.movie.film.bean.PlayingBean;
import com.bw.movie.film.bean.PopularBean;
import com.bw.movie.film.bean.PraiseBean;

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
public interface FilmService {

     //请求轮播数据  内网
     @GET("movie/v1/findHotMovieList")
     Observable<CarouselBean> getCarouselBeanObservable(@Query("page") int page, @Query("count") int count);


     //请求热门电影数据  内网
     @GET("movie/v1/findHotMovieList")
     Observable<PopularBean> getPopularBeanObservable(@Query("page") int page, @Query("count") int count);


     //请求正在热映数据 内网
    @GET("movie/v1/findReleaseMovieList")
    Observable<HotPlayBean> getHotPlayBeanObservable(@Query("page") int page, @Query("count") int count);


    //请求 正在上映 || 即将上映 数据  内网
    @GET("movie/v1/findComingSoonMovieList")
    Observable<PlayingBean> getPlayingBeanObservable(@Query("page") int page, @Query("count") int count);


    //通过ID 查找 电影详情页面
    @GET("movie/v1/findMoviesDetail")
    Observable<DetailBean> getDetailBeanObservable(@Query("movieId") int movieId);


    //请求评论接口
    @GET("movie/v1/findAllMovieComment")
    Observable<CommentBean> getCommentBeanObservable(@Query("movieId") int movieId ,@Query("page") int page ,@Query("count") int count);


    //关注电影
    @GET("movie/v1/verify/followMovie")
    Observable<FollowBean> getFollowBeanObservable(@Query("movieId") int movieId );

    //取消关注
    @GET("movie/v1/verify/cancelFollowMovie")
    Observable<CancelFollowMovieBean> getCancelFollowMovieBeanObservable(@Query("movieId") int movieId );


    //根据电影Id 查询影院
    @GET("movie/v1/findCinemasListByMovieId")
    Observable<CinemaBean> getCinemaBeanObservable(@Query("movieId") int movieId );

    @POST("movie/v1/verify/commentReply")
    @FormUrlEncoded
    Observable<InputcommentsBean> getInputcomments(@Field("commentId") int commentId,@Field("replyContent") String replyContent);


    //点赞
    @POST("movie/v1/verify/movieCommentGreat")
    @FormUrlEncoded
    Observable<PraiseBean> getPraiseBeanObservable(@Field("commentId") int commentId );



}
