package com.bw.movie.login.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.login.service.LoginService;
import com.bw.movie.util.HttpCallBack;
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
public class LoginModel {
    public void getLogin(String phone, String pwd, final HttpCallBack<LoginUser> httpCallBack) {
        OkHttpUtil.get().createa(LoginService.class).getLogn(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginUser>(httpCallBack));
    }
}
