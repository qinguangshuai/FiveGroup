package com.bw.movie.cinema.recommend.model;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.recommend.bean.RecommendBean;
import com.bw.movie.cinema.recommend.service.RecommendService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class recommendModel extends BaseModel {
    public void getRecommned(String longitude, String latitude, int page, int count, final HttpCallBack<RecommendBean> httpCallBack){
        OkHttpUtil.get().createa(RecommendService.class).getRecommned(longitude, latitude, page, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<RecommendBean>(httpCallBack));
    }
}
