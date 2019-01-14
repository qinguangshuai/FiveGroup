package com.bw.movie.cinema.cannelfollow.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.cannelfollow.service.CannelFollowService;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/27    17:59
 * author:张文龙(张文龙)
 * fileName:CannelFollowModel
 */
public class CannelFollowModel {

    public void getCannelFollow(int cinemaId, final HttpCallBack<FollowBean> httpCallBack) {
        OkHttpUtil.get().createa(CannelFollowService.class)
                .getCannelFollow(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<FollowBean>(httpCallBack));
    }
}
