package com.bw.movie.film.service;

import com.bw.movie.film.bean.CarouselBean;
import com.bw.movie.film.bean.CommentBean;
import com.bw.movie.film.bean.DetailBean;
import com.bw.movie.film.bean.HotPlayBean;
import com.bw.movie.film.bean.PlayingBean;
import com.bw.movie.film.bean.PopularBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
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


}
