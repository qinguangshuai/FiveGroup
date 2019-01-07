package com.bw.movie.film.details.callback;

import com.bw.movie.film.details.bean.CancelFollowMovieBean;

/*
 *作者:ash
 *TODO:
 *
 */public interface CancelFollowMovieCallBack {
     void success(CancelFollowMovieBean cancelFollowMovieBean);
     void error(String s);
}
