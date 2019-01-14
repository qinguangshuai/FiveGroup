package com.bw.movie.my.mysound;

import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/30    8:34
 * author:Therefore(Lenovo)
 * fileName:MySoundModel
 */
public class MySoundModel {
    public void getSound(int page, final HttpCallBack<MySoundUser> httpCallBack) {
        OkHttpUtil.get().createa(MySoundService.class).getSound(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<MySoundUser>(httpCallBack){
                    @Override
                    public void onNext(MySoundUser mySoundUser) {
                        super.onNext(mySoundUser);
                        BaseEvent.post(new ChuanUser());
                    }
                });
    }

}
