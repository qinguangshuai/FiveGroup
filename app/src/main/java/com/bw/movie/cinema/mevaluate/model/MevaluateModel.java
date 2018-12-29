package com.bw.movie.cinema.mevaluate.model;

import com.bw.movie.cinema.mevaluate.bean.MevaluateBean;
import com.bw.movie.cinema.mevaluate.service.MevaluateService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/29    8:31
 * author:张文龙(张文龙)
 * fileName:MevaluateModel
 */
public class MevaluateModel {
    public void getMevaluate(int cinemaId, int page, int count, final IsMevaluate isMevaluate) {
        OkHttpUtil.get().createa(MevaluateService.class)
                .getMevaluate(cinemaId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MevaluateBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MevaluateBean mevaluateBean) {
                        isMevaluate.getModevalute(mevaluateBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IsMevaluate {
        void getModevalute(MevaluateBean mevaluateBean);
    }
}
