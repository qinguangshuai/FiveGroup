package com.bw.movie.my.mysound;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/30    8:34
 * author:Therefore(Lenovo)
 * fileName:MySoundModel
 */
public class XiSoundModel {
    public void getSound(final HttpCallBack<XiSoundUser> httpCallBack) {
        OkHttpUtil.get().createa(XiSoundService.class).getXi()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<XiSoundUser>(httpCallBack));
    }
}
