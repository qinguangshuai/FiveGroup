package com.bw.movie.film.show.hot.callback;

import com.bw.movie.film.show.hot.bean.HotPlayBean;

/*
 *作者:ash
 *TODO: 热门电影
 *
 */public interface HotPlayCallBack {
     void success(HotPlayBean hotPlayBean);
     void error(String message);


}
