package com.bw.movie.my.attention.model;

import android.os.Handler;

import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.my.attention.service.AttFilmService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/28    15:11
 * author:Therefore(Lenovo)
 * fileName:AttCinemaModel
 */
public class AttFilmModel extends BaseModel {
    public void getFilm(int page, final HttpCallBack<MyAttFilmUser> httpCallBack){
        OkHttpUtil.get().createa(AttFilmService.class).getFilm(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyAttFilmUser>(httpCallBack){
                    @Override
                    public void onNext(MyAttFilmUser myAttFilmUser) {
                        if (myAttFilmUser.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    BaseEvent.post(new ChuanUser());
                                }
                            },100);
                        } else {
                            super.onNext(myAttFilmUser);
                        }
                    }
                });
    }
}
