package com.bw.movie.film.m;

import com.bw.movie.film.bean.FollowBean;
import com.bw.movie.film.bean.HotPlayBean;

/*
 *作者:ash
 *TODO:
 *
 */public interface FollowCallBack {
    void success(FollowBean followBean);
    void error(String message);
}
