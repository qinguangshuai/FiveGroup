package com.bw.movie.cinema.Particulars;

import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 影院内容轮播model
 */
public class ParticularsModel {
    public void getParticulars(int page, int count, final HttpCallBack<ParticularsBean> httpCallBack) {
        OkHttpUtil.get().createa(ParticularsService.class).getParticulars(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ParticularsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ParticularsBean particularsBean) {
                        httpCallBack.onSuccess(particularsBean);
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
