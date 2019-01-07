package com.bw.movie.film.show.popular.model;

import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.film.show.popular.callback.PopularCallBack;
import com.bw.movie.film.show.popular.service.PopularService;
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
public class PopularModel {


    //热门电影 请求数据回调
    public void getPopularBeanObservable(int page, int count, final PopularCallBack popularCallBack) {
        OkHttpUtil
                .get()
                .createa(PopularService.class)
                .getPopularBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PopularBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PopularBean popularBean) {
                        popularCallBack.success(popularBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        popularCallBack.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}


