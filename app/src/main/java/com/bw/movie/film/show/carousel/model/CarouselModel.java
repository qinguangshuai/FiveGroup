package com.bw.movie.film.show.carousel.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.film.show.carousel.bean.CarouselBean;
import com.bw.movie.film.show.carousel.service.CarouserlService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *作者:ash
 *TODO:
 *   model 层
 */
public class CarouselModel {

    //轮播图请求数据回调
    public void getCarouselBeanObservable(int page, int count, final HttpCallBack<CarouselBean> httpCallBack) {
        OkHttpUtil
                .get()
                .createa(CarouserlService.class)
                .getCarouselBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CarouselBean>(httpCallBack));

    }
}


