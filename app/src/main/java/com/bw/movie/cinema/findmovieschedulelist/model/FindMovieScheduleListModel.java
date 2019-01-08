package com.bw.movie.cinema.findmovieschedulelist.model;

import com.bw.movie.cinema.findmovieschedulelist.bean.FindMovieScheduleListBean;
import com.bw.movie.cinema.findmovieschedulelist.service.FindMovieScheduleListService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/27    18:55
 * author:张文龙(张文龙)
 * fileName:FindMovieScheduleListModel
 */
public class FindMovieScheduleListModel {

    public void getFindMovieScheduleList(int cinemasId, int movieId, final HttpCallBack<FindMovieScheduleListBean> httpCallBack) {
        OkHttpUtil.get().createa(FindMovieScheduleListService.class).getFindMovieScheduleList(cinemasId, movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindMovieScheduleListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindMovieScheduleListBean findMovieScheduleListBean) {
                        httpCallBack.onSuccess(findMovieScheduleListBean);
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
