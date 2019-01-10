package com.bw.movie.my.message.model;

import android.content.Intent;
import android.os.Handler;

import com.bw.movie.MyApp;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.message.bean.MyMessageEntity;
import com.bw.movie.my.message.service.MyMessageService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/*
MyMessageModel
* */
public class MyMessageModel {

    public void getMessage(final HttpCallBack<MyMessageEntity> httpCallBack){
        OkHttpUtil.get().createa(MyMessageService.class).getMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyMessageEntity>(httpCallBack){
                    @Override
                    public void onNext(MyMessageEntity myMessageEntity) {
                        if (myMessageEntity.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();
                                }
                            }, 2000);
                        } else {
                            super.onNext(myMessageEntity);
                        }
                    }
                });

    }

}
