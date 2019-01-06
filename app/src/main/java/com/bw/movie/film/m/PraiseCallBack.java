package com.bw.movie.film.m;

import com.bw.movie.film.bean.PraiseBean;

/*
 *作者:ash
 *TODO:
 *
 */
public interface PraiseCallBack {
    void success(PraiseBean praiseBean);
    void error(String message);
}
