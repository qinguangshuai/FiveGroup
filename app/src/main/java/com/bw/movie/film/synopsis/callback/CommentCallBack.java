package com.bw.movie.film.synopsis.callback;

import com.bw.movie.film.synopsis.bean.CommentBean;

/*
 *作者:ash
 *TODO:
 *
 */
public interface CommentCallBack {
    void success(CommentBean commentBean);
    void error(String message);
}
