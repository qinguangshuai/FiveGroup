package com.bw.movie.registe.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.registe.bean.RegisteUser;
import com.bw.movie.registe.service.RegisteSrevice;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/26    18:40
 * author:Therefore(Lenovo)
 * fileName:RegisteModel
 */
public class RegisteModel {

    public void postRegiste(String nickName, String phone, String pwd, String pwd2, int sex, String birthday, String imei, String ua, String screenSize, String os, String email, final HttpCallBack<RegisteUser> httpCallBack) {
        OkHttpUtil.get().createa(RegisteSrevice.class).postRegiste(nickName, phone, pwd, pwd2, sex, birthday, imei, ua, screenSize, os, email)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<RegisteUser>(httpCallBack));
    }

}
