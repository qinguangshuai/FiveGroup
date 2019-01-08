package com.bw.movie.cinema.Particulars.model;

import android.util.Log;

import com.bw.movie.cinema.Particulars.bean.MovieListByCinemaIdBean;
import com.bw.movie.cinema.Particulars.service.MovieListByCinemaIdService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * date:2018/12/27    16:06
 * author:张文龙(张文龙)
 * fileName:MovieListByCinemaIdModel
 */
public class MovieListByCinemaIdModel {

    public void getMovieByBean(int cinemaId, final HttpCallBack<MovieListByCinemaIdBean> httpCallBack) {
        OkHttpUtil.get().createa(MovieListByCinemaIdService.class).getMovieByBean(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieListByCinemaIdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieListByCinemaIdBean movieListByCinemaIdBean) {
                       httpCallBack.onSuccess(movieListByCinemaIdBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       httpCallBack.onFailer("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
