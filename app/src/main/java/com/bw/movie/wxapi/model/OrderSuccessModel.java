package com.bw.movie.wxapi.model;

import com.bw.movie.base.BaseObserver;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.WeiXinUtil;
import com.bw.movie.wxapi.bean.OrderSuccessBean;
import com.bw.movie.wxapi.service.OrderSuccessService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderSuccessModel {
    public void getOrder(int payType, String orderId, final HttpCallBack<OrderSuccessBean> httpCallBack) {
        OkHttpUtil.get().createa(OrderSuccessService.class).getOrderSuccess(payType, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<OrderSuccessBean>(httpCallBack));

    }
}
