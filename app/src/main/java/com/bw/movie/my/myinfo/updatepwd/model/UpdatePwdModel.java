package com.bw.movie.my.myinfo.updatepwd.model;

import android.content.Intent;
import android.os.Handler;

import com.bw.movie.MyApp;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.myinfo.updatepwd.bean.UpdatePwdEntity;
import com.bw.movie.my.myinfo.updatepwd.service.UpdatePwdService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
   修改密码model层
* */
public class UpdatePwdModel {
    public void getPwd(String oldPwd, String newPwd, String newPwd2, HttpCallBack<UpdatePwdEntity> httpCallBack) {
        OkHttpUtil.get().createa(UpdatePwdService.class).getPwd(oldPwd, newPwd, newPwd2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UpdatePwdEntity>(httpCallBack){
                    @Override
                    public void onNext(UpdatePwdEntity updatePwdEntity) {
                        if (updatePwdEntity.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();
                                }
                            }, 2000);
                        } else {
                            super.onNext(updatePwdEntity);
                        }
                    }
                });
    }
}
