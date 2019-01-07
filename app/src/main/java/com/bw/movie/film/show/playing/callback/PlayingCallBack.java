package com.bw.movie.film.show.playing.callback;

import com.bw.movie.film.show.playing.playing.PlayingBean;

/*
 *作者:ash
 *TODO:  正在上映
 *
 */public interface PlayingCallBack {

     void success(PlayingBean playingBean);
     void error(String message);

}
