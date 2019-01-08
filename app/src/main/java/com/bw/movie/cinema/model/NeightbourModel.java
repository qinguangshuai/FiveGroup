package com.bw.movie.cinema.model;


import com.bw.movie.cinema.bean.neightbourbean.NeightbourBean;
import com.bw.movie.cinema.service.NeighbourService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NeightbourModel {
    public void getNeightbour(int page, int count, final HttpCallBack<NeightbourBean> httpCallBack) {
        OkHttpUtil.get().createa(NeighbourService.class).getNeightbour(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeightbourBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NeightbourBean neightbourBean) {
                        httpCallBack.onSuccess(neightbourBean);
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
