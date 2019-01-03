package com.bw.movie.login.model;

import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.login.bean.XinUser;
import com.bw.movie.login.service.LoginService;
import com.bw.movie.login.service.XinService;
import com.bw.movie.my.mysound.XiSoundModel;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/26    8:25
 * author:Therefore(Lenovo)
 * fileName:LoginModel
 */
public class XinModel {
    public void getXinGe(String token, int os, final HttpXinGe httpXi) {
        OkHttpUtil.get().createa(XinService.class).getXinGe(token, os)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XinUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XinUser xinUser) {
                        httpXi.thisLogin(xinUser);
                    }

                    @Override
                        public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface HttpXinGe {
        void thisLogin(XinUser xinUser);
    }

}
