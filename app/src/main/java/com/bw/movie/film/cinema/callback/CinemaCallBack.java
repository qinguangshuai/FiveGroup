package com.bw.movie.film.cinema.callback;

import com.bw.movie.film.cinema.bean.CinemaBean;

/*
 *作者:ash
 *TODO:
 *
 */
public interface CinemaCallBack {
    void success(CinemaBean cinemaBean);
    void error(String message);
 }
