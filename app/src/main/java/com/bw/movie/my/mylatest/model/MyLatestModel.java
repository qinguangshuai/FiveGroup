package com.bw.movie.my.mylatest.model;

import com.bw.movie.my.mylatest.bean.MyLatestUser;
import com.bw.movie.my.mylatest.service.MyLatestService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/27    19:37
 * author:Therefore(Lenovo)
 * fileName:MyLatestModel
 */
public class MyLatestModel {
    public void getVersion(final HttpCallBack<MyLatestUser> httpCallBack) {
        OkHttpUtil.get().createa(MyLatestService.class).getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyLatestUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyLatestUser myLatestUser) {
                        httpCallBack.onSuccess(myLatestUser);
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
