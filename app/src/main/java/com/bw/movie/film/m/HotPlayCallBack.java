package com.bw.movie.film.m;

import com.bw.movie.film.bean.CarouselBean;
import com.bw.movie.film.bean.HotPlayBean;

/*
 *作者:ash
 *TODO: 热门电影
 *
 */public interface HotPlayCallBack {
     void success(HotPlayBean hotPlayBean);
     void error(String message);


}
