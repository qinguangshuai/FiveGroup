package com.bw.movie.film.details.model;

import com.bw.movie.film.details.bean.DetailBean;
import com.bw.movie.film.details.callback.DetailCallBack;
import com.bw.movie.film.details.service.DetailService;
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
    public void getDetailBeanObservable(int id , final DetailCallBack detailCallBack){
        OkHttpUtil
        .get()
        .createa(DetailService.class)
        .getDetailBeanObservable(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<DetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DetailBean detailBean) {
                detailCallBack.success(detailBean);
            }

            @Override
            public void onError(Throwable e) {
                detailCallBack.error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }
}


