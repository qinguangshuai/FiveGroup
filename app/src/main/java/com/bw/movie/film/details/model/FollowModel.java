package com.bw.movie.film.details.model;

import android.content.Intent;
import android.os.Handler;

import com.bw.movie.MyApp;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.error.AppManager;
import com.bw.movie.film.details.bean.FollowBean;
import com.bw.movie.film.details.service.FollowService;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *作者:ash
 *TODO:
 *   model 层
 */
public class FollowModel {

    //关注
    public void getFollowBeanObservable(int follow, final HttpCallBack<FollowBean> httpCallBack){
        OkHttpUtil
                .get()
                .createa(FollowService.class)
                .getFollowBeanObservable(follow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<FollowBean>(httpCallBack){
                    @Override
                    public void onNext(FollowBean followBean) {
                        if (followBean.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();
                                }
                            }, 100);
                        } else {
                            super.onNext(followBean);
                        }
                    }
                });
    }
}


