package com.bw.movie.film.details.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.film.details.bean.DetailBean;
import com.bw.movie.film.details.service.DetailService;
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
public class DetailModel {

    //Id 展示电影详情
    public void getDetailBeanObservable(int id , final HttpCallBack<DetailBean> httpCallBack){
        OkHttpUtil
        .get()
        .createa(DetailService.class)
        .getDetailBeanObservable(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BaseObserver<DetailBean>(httpCallBack));
    }
}


