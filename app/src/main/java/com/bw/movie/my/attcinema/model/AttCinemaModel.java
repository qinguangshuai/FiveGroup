package com.bw.movie.my.attcinema.model;

import android.os.Handler;

import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attcinema.service.AttCinemaService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/28    15:11
 * author:Therefore(Lenovo)
 * fileName:AttCinemaModel
 */
public class AttCinemaModel extends BaseModel {
    public void getCinema(int page, final HttpCallBack<AttCinemaUser> httpCallBack){
        OkHttpUtil.get().createa(AttCinemaService.class).getCinema(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<AttCinemaUser>(httpCallBack){
                    @Override
                    public void onNext(AttCinemaUser attCinemaUser) {
                        if (attCinemaUser.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /*MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();*/
                                    BaseEvent.post(new ChuanUser());
                                }
                            },100);
                        } else {
                            super.onNext(attCinemaUser);
                        }
                    }
                });
    }
}
