package com.bw.movie.my.message.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.my.message.bean.MyMessageEntity;
import com.bw.movie.my.message.service.MyMessageService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;
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
                .subscribe(new BaseObserver<MyMessageEntity>(httpCallBack));

    }

}
