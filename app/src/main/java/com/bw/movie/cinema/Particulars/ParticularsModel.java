package com.bw.movie.cinema.Particulars;

import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 影院内容轮播model
 */
public class ParticularsModel {
    public void getParticulars(int page, int count, final isParticulars isParticulars) {
        OkHttpUtil.get().createa(ParticularsService.class).getParticulars(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ParticularsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ParticularsBean particularsBean) {
                        isParticulars.getParticulars(particularsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface isParticulars {
        void getParticulars(ParticularsBean particularsBean);
    }
}
