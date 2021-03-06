package com.bw.movie.my.myoption.model;

import android.os.Handler;

import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.my.myoption.bean.MyOptionEntity;
import com.bw.movie.my.myoption.service.MyOptionService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
   我的意见反馈model层
* */
public class MyOptionModel extends BaseModel {
    public void getOpition(String content, final HttpCallBack<MyOptionEntity> httpCallBack) {
        OkHttpUtil.get().createa(MyOptionService.class).getOption(content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyOptionEntity>(httpCallBack){
                    @Override
                    public void onNext(MyOptionEntity myOptionEntity) {
                        if (myOptionEntity.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /*MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();*/
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 100);
                        } else {
                            super.onNext(myOptionEntity);
                        }
                    }
                });
    }
}
