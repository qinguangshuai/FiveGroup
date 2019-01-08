package com.bw.movie.film.show.carousel.callback;

import com.bw.movie.film.show.carousel.bean.CarouselBean;

/*
 *作者:ash
 *TODO:  轮播callback
 *
 */public interface CarouseCallBack  {

     void success(CarouselBean carouselBean);
     void error(String message);

}
