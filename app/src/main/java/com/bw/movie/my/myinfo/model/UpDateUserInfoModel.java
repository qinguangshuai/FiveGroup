package com.bw.movie.my.myinfo.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.my.myinfo.bean.UpDateUserInfoEntity;
import com.bw.movie.my.myinfo.service.UpDateUserInfoService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
*  修改用户信息的model层
* */
public class UpDateUserInfoModel  {

    public void getUserInfo(String nickName,int sex,String email,final HttpCallBack<UpDateUserInfoEntity> httpCallBack){
        OkHttpUtil.get().createa(UpDateUserInfoService.class).getUserInfo(nickName,sex,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UpDateUserInfoEntity>(httpCallBack));
    }


}
