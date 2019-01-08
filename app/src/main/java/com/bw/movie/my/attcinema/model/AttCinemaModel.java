package com.bw.movie.my.attcinema.model;

import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attcinema.service.AttCinemaService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/28    15:11
 * author:Therefore(Lenovo)
 * fileName:AttCinemaModel
 */
public class AttCinemaModel {
    public void getCinema(int page, final HttpCallBack<AttCinemaUser> httpCallBack){
        OkHttpUtil.get().createa(AttCinemaService.class).getCinema(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AttCinemaUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AttCinemaUser attCinemaUser) {
                        httpCallBack.onSuccess(attCinemaUser);
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
