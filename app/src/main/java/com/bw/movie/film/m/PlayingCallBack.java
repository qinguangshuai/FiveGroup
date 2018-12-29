package com.bw.movie.film.m;

import com.bw.movie.film.bean.CarouselBean;
import com.bw.movie.film.bean.PlayingBean;

/*
 *作者:ash
 *TODO:  正在上映
 *
 */public interface PlayingCallBack {

     void success(PlayingBean playingBean);
     void error(String message);


}
