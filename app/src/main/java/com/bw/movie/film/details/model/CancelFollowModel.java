package com.bw.movie.film.details.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.film.details.bean.CancelFollowMovieBean;
import com.bw.movie.film.details.service.CanceFollowService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *作者:ash
 *TODO:
 *   model 层
 */
public class CancelFollowModel {

    //取消关注
    public void getCancelFollowMovieBeanObservable(int can , final HttpCallBack<CancelFollowMovieBean> httpCallBack){
        OkHttpUtil
                .get()
                .createa(CanceFollowService.class)
                .getCancelFollowMovieBeanObservable(can)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CancelFollowMovieBean>(httpCallBack));
    }
}


