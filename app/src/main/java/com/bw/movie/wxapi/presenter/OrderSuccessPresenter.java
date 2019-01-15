package com.bw.movie.wxapi.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.wxapi.bean.OrderSuccessBean;
import com.bw.movie.wxapi.model.OrderSuccessModel;

public class OrderSuccessPresenter extends BasePresenter {

 private OrderSuccessModel mOrderSuccessModel;

    public OrderSuccessPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mOrderSuccessModel = new OrderSuccessModel();
    }
    public void getOeder(int payType ,String orderId){
       mOrderSuccessModel.getOrder(payType, orderId, new HttpCallBack<OrderSuccessBean>() {
           @Override
           public void onSuccess(OrderSuccessBean name) {
               getiBaseView().onDataSuccess(name);
           }

           @Override
           public void onFailer(String result) {

           }
       });
    }
}
