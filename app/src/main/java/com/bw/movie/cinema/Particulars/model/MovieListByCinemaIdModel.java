package com.bw.movie.cinema.Particulars.model;

import android.util.Log;

import com.bw.movie.cinema.Particulars.bean.MovieListByCinemaIdBean;
import com.bw.movie.cinema.Particulars.service.MovieListByCinemaIdService;
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

    public void getMovieByBean(int cinemaId, final isMovieByBean isMovieByBean) {
        OkHttpUtil.get().createa(MovieListByCinemaIdService.class).getMovieByBean(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieListByCinemaIdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieListByCinemaIdBean movieListByCinemaIdBean) {
                        isMovieByBean.getMovieByBean(movieListByCinemaIdBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("zwl", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface isMovieByBean {
        void getMovieByBean(MovieListByCinemaIdBean movieListByCinemaIdBean);
    }
}
