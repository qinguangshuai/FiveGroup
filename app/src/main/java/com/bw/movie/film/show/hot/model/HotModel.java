package com.bw.movie.film.show.hot.model;

import com.bw.movie.film.show.hot.bean.HotPlayBean;
import com.bw.movie.film.show.hot.callback.HotPlayCallBack;
import com.bw.movie.film.show.hot.service.HotService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *作者:ash
 *TODO:
 *  热门电影   model 层
 */
public class HotModel {

    //正在热映 请求数据回调
    public void getHotPlayBeanObservable(int page, int count, final HotPlayCallBack hotPlayCallBack) {
        OkHttpUtil
                .get()
                .createa(HotService.class)
                .getHotPlayBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotPlayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotPlayBean hotPlayBean) {
                        hotPlayCallBack.success(hotPlayBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hotPlayCallBack.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }






}


