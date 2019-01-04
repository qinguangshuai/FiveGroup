package com.bw.movie.film.m;

import com.bw.movie.film.bean.CinemaBean;

/*
 *作者:ash
 *TODO:
 *
 */
public interface CinemaCallBack {
    void success(CinemaBean cinemaBean);
    void error(String message);
 }
