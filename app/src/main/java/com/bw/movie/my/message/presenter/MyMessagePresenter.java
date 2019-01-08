package com.bw.movie.my.message.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.message.bean.MyMessageEntity;
import com.bw.movie.my.message.model.MyMessageModel;
import com.bw.movie.my.message.view.MyMessageView;
import com.bw.movie.util.HttpCallBack;

/*
 * MyMessagePresenter
 * */
public class MyMessagePresenter extends BasePresenter<MyMessageView> {

    private MyMessageModel myMessageModel;

    public MyMessagePresenter(MyMessageView iBaseView) {
        super(iBaseView);
        myMessageModel = new MyMessageModel();
    }

    public void getMessage() {
        myMessageModel.getMessage(new HttpCallBack<MyMessageEntity>() {
            @Override
            public void onSuccess(MyMessageEntity name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
