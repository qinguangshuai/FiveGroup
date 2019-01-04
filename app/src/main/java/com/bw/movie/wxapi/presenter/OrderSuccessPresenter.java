package com.bw.movie.wxapi.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.wxapi.bean.OrderSuccessBean;
import com.bw.movie.wxapi.model.OrderSuccessModel;

public class OrderSuccessPresenter extends BasePresenter {
 private OrderSuccessModel orderSuccessModel;
    public OrderSuccessPresenter(IBaseView iBaseView) {
        super(iBaseView);
        orderSuccessModel = new OrderSuccessModel();
    }
    public void getOeder(int payType ,String orderId){
       orderSuccessModel.getOrder(payType, orderId, new OrderSuccessModel.getOrder() {
           @Override
           public void getOreders(OrderSuccessBean orderSuccessBean) {
               getiBaseView().onDataSuccess(orderSuccessBean);
           }
       });
    }
}
