package com.bw.movie.film.m;

import com.bw.movie.film.bean.CancelFollowMovieBean;

/*
 *作者:ash
 *TODO:
 *
 */public interface CancelFollowMovieCallBack {
     void success(CancelFollowMovieBean cancelFollowMovieBean);
     void error(String s);
}
