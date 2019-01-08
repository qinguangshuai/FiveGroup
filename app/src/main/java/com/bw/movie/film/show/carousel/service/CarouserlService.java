package com.bw.movie.film.show.carousel.service;

import com.bw.movie.film.show.carousel.bean.CarouselBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *作者:ash
 *TODO:
 *   baseservice
 */
public interface CarouserlService {
     //请求轮播数据  内网
     @GET("movie/v1/findHotMovieList")
     Observable<CarouselBean> getCarouselBeanObservable(@Query("page") int page, @Query("count") int count);
}
