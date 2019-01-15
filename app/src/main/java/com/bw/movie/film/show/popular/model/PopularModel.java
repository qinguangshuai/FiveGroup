package com.bw.movie.film.show.popular.model;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.film.show.popular.service.PopularService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *作者:ash
 *TODO:
 *   model 层
 */
public class PopularModel extends BaseModel {


    //热门电影 请求数据回调
    public void getPopularBeanObservable(int page, int count, HttpCallBack<PopularBean> httpCallBack) {
        OkHttpUtil
                .get()
                .createa(PopularService.class)
                .getPopularBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PopularBean>(httpCallBack));
    }
}


