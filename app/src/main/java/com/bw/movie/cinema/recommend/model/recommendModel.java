package com.bw.movie.cinema.recommend.model;

import com.bw.movie.base.BaseModel;
import com.bw.movie.cinema.recommend.bean.RecommendBean;
import com.bw.movie.cinema.recommend.service.RecommendService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class recommendModel extends BaseModel {
    public void getRecommned(String longitude, String latitude, int page, int count, final HttpCallBack<RecommendBean> httpCallBack){
        OkHttpUtil.get().createa(RecommendService.class).getRecommned(longitude, latitude, page, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RecommendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendBean recommendBean) {
                        httpCallBack.onSuccess(recommendBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                     httpCallBack.onFailer("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
