package com.bw.movie.cinema.good.model;

import android.os.Handler;

import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.cinema.good.bean.GoodBean;
import com.bw.movie.cinema.good.service.GoodService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 评论点赞(model)
 */
public class GoodModel extends BaseModel {
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
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 100);
                        } else {
                            super.onNext(goodBean);
                        }
                    }
                });
    }

}
