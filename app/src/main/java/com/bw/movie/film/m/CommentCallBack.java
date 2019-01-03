package com.bw.movie.film.m;

import com.bw.movie.film.bean.CommentBean;

/*
 *作者:ash
 *TODO:
 *
 */
public interface CommentCallBack {
    void success(CommentBean commentBean);
    void error(String message);
}
