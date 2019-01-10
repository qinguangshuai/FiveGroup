package com.bw.movie.film.cinema.model;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.film.cinema.bean.CinemaBean;
import com.bw.movie.film.cinema.service.CinemaService;
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
public class CinemaModle extends BaseModel {

    //查询影院
    public void getCinemaBeanObservable(int id, final HttpCallBack<CinemaBean> httpCallBack){

        OkHttpUtil
                .get()
                .createa(CinemaService.class)
                .getCinemaBeanObservable(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CinemaBean>(httpCallBack));
    }
}


