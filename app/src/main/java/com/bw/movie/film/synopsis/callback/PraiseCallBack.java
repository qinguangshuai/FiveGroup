package com.bw.movie.film.synopsis.callback;

import com.bw.movie.film.synopsis.bean.PraiseBean;

/*
 *作者:ash
 *TODO:
 *
 */
public interface PraiseCallBack {
    void success(PraiseBean praiseBean);
    void error(String message);
}
