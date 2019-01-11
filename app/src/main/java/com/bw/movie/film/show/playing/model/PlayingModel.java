package com.bw.movie.film.show.playing.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.film.show.playing.playing.PlayingBean;
import com.bw.movie.film.show.playing.callback.PlayingCallBack;
import com.bw.movie.film.show.playing.service.PlayingService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *作者:ash
 *TODO:
 *   model 层
 */
public class PlayingModel {


    //正在上映  请求数据回调
    public void getPlayingBeanObservable(int page, int count, HttpCallBack<PlayingBean> httpCallBack) {
        OkHttpUtil
                .get()
                .createa(PlayingService.class)
                .getPlayingBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PlayingBean>(httpCallBack));
    }

}


