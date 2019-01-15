package com.bw.movie.cinema.Particulars.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.Particulars.bean.MovieListByCinemaIdBean;
import com.bw.movie.cinema.Particulars.service.MovieListByCinemaIdService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
                .subscribe(new BaseObserver<MovieListByCinemaIdBean>(httpCallBack));
    }

}
