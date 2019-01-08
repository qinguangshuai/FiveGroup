package com.bw.movie.film.show.carousel.model;

import com.bw.movie.film.show.carousel.bean.CarouselBean;
import com.bw.movie.film.show.carousel.service.CarouserlService;
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
public class CarouselModel {

    //轮播图请求数据回调
    public void getCarouselBeanObservable(int page, int count, final HttpCallBack<CarouselBean> httpCallBack) {
        OkHttpUtil
                .get()
                .createa(CarouserlService.class)
                .getCarouselBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CarouselBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CarouselBean carouselBean) {
                        httpCallBack.onSuccess(carouselBean);
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


