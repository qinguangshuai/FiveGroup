package com.bw.movie.cinema.follow.model;

import android.os.Handler;

import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.cinema.follow.service.FollowService;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/27    17:29
 * author:张文龙(张文龙)
 * fileName:FollowModel
 */
public class FollowModel {

    public void getFollow(int cinemaId, final HttpCallBack<FollowBean> httpCallBack) {
        OkHttpUtil.get().createa(FollowService.class).getFollow(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<FollowBean>(httpCallBack){
                    @Override
                    public void onNext(FollowBean followBean) {
                        if (followBean.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    //AppManager.getAppManager().finishAllActivity();
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 100);
                        } else {
                            super.onNext(followBean);
                        }
                    }
                });
    }

}
