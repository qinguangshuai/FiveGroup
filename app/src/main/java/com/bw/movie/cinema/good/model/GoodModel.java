package com.bw.movie.cinema.good.model;

import android.content.Intent;
import android.os.Handler;

import com.bw.movie.MyApp;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.good.bean.GoodBean;
import com.bw.movie.cinema.good.service.GoodService;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 评论点赞(model)
 */
public class GoodModel {
    public void getGood(int commentId, final HttpCallBack<GoodBean> httpCallBack){
        OkHttpUtil.get().createa(GoodService.class).getGoods(commentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<GoodBean>(httpCallBack){
                    @Override
                    public void onNext(GoodBean goodBean) {
                        if (goodBean.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();
                                }
                            }, 1000);
                        } else {
                            super.onNext(goodBean);
                        }
                    }
                });
    }

}
