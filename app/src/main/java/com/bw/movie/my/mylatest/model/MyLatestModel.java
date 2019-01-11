package com.bw.movie.my.mylatest.model;

import android.content.Intent;
import android.os.Handler;

import com.bw.movie.MyApp;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.mylatest.bean.MyLatestUser;
import com.bw.movie.my.mylatest.service.MyLatestService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

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
                .subscribe(new BaseObserver<MyLatestUser>(httpCallBack){
                    @Override
                    public void onNext(MyLatestUser myLatestUser) {
                        if (myLatestUser
                                .getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();
                                }
                            }, 1000);
                        } else {
                            super.onNext(myLatestUser);
                        }
                    }
                });
    }
}
