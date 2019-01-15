package com.bw.movie.login.model;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.login.bean.XinUser;
import com.bw.movie.login.service.XinService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/26    8:25
 * author:Therefore(Lenovo)
 * fileName:LoginModel
 */
public class XinModel extends BaseModel {
    public void getXinGe(String token, int os, final HttpCallBack<XinUser> httpCallBack) {
        OkHttpUtil.get().createa(XinService.class).getXinGe(token, os)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<XinUser>(httpCallBack));
    }


}
