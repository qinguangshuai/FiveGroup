package com.bw.movie.wxapi.model;

import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.WeiXinUtil;
import com.bw.movie.wxapi.bean.OrderSuccessBean;
import com.bw.movie.wxapi.service.OrderSuccessService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderSuccessModel {
    public void getOrder(int payType, String orderId, final getOrder getOrder) {
        OkHttpUtil.get().createa(OrderSuccessService.class).getOrderSuccess(payType, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderSuccessBean orderSuccessBean) {
                        WeiXinUtil.weiXinPay(orderSuccessBean);
                        getOrder.getOreders(orderSuccessBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface getOrder {
        void getOreders(OrderSuccessBean orderSuccessBean);
    }
}
