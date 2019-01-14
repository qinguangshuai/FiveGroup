package com.bw.movie.my.mysound;

import android.os.Handler;

import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
                        if (mySoundUser.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 100);
                        } else {
                            super.onNext(mySoundUser);
                        }
                    }
                });
    }

}
