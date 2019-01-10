package com.bw.movie.my.myoption.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.my.myoption.bean.MyOptionEntity;
import com.bw.movie.my.myoption.service.MyOptionService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;
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
                .subscribe(new BaseObserver<MyOptionEntity>(httpCallBack));

    }

}
