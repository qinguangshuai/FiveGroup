package com.bw.movie.film.show.popular.callback;

import com.bw.movie.film.show.popular.bean.PopularBean;

/*
 *作者:ash
 *TODO:
 *  热映
 */public interface PopularCallBack {

     void success(PopularBean popularBean);
     void error(String message);


}
