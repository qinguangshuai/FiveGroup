package com.bw.movie.my.myoption.model;

import android.content.Intent;
import android.os.Handler;

import com.bw.movie.MyApp;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.myoption.bean.MyOptionEntity;
import com.bw.movie.my.myoption.service.MyOptionService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
   我的意见反馈model层
* */
public class MyOptionModel {
    public void getOpition(String content, final HttpCallBack<MyOptionEntity> httpCallBack) {
        OkHttpUtil.get().createa(MyOptionService.class).getOption(content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyOptionEntity>(httpCallBack){
                    @Override
                    public void onNext(MyOptionEntity myOptionEntity) {
                        if (myOptionEntity.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /*MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();*/
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 100);
                        } else {
                            super.onNext(myOptionEntity);
                        }
                    }
                });
    }
}
