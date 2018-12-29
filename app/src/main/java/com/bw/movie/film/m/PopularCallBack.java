package com.bw.movie.film.m;

import com.bw.movie.film.bean.CarouselBean;
import com.bw.movie.film.bean.PopularBean;

/*
 *作者:ash
 *TODO:
 *  热映
 */public interface PopularCallBack {

     void success(PopularBean popularBean);
     void error(String message);


}
