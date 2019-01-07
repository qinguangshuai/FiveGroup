package com.bw.movie.film.details.callback;

import com.bw.movie.film.details.bean.FollowBean;

/*
 *作者:ash
 *TODO:
 *
 */public interface FollowCallBack {
    void success(FollowBean followBean);
    void error(String message);
}
