package com.bw.movie.cinema.findmovieschedulelist.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.findmovieschedulelist.bean.FindMovieScheduleListBean;
import com.bw.movie.cinema.findmovieschedulelist.service.FindMovieScheduleListService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
                .subscribe(new BaseObserver<FindMovieScheduleListBean>(httpCallBack));
    }
}
