package com.bw.movie.cinema.model;


import com.bw.movie.cinema.bean.neightbourbean.NeightbourBean;
import com.bw.movie.cinema.service.NeighbourService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NeightbourModel {
    public void getNeightbour(int page, int count, final isSuccess isSuccess) {
        OkHttpUtil.get().createa(NeighbourService.class).getNeightbour(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeightbourBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NeightbourBean neightbourBean) {
                    isSuccess.getSuccess(neightbourBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface isSuccess {
        void getSuccess(NeightbourBean neightbourBean);
    }

}
