package com.bw.movie.cinema.mdetails.model;

import android.os.Message;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.mdetails.bean.MdetailsBean;
import com.bw.movie.cinema.mdetails.service.MdetailsService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/28    18:33
 * author:张文龙(张文龙)
 * fileName:MdetailsModel
 */
public class MdetailsModel {
    public void getMdetails(int cinemaId, final HttpCallBack<MdetailsBean> httpCallBack) {
        OkHttpUtil.get().createa(MdetailsService.class).getMdtails(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MdetailsBean>(httpCallBack));
    }

}
