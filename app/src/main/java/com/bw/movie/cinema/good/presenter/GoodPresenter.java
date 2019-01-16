package com.bw.movie.cinema.good.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.good.bean.GoodBean;
import com.bw.movie.cinema.good.model.GoodModel;
import com.bw.movie.net.HttpCallBack;

public class GoodPresenter extends BasePresenter {
    private GoodModel mGoodModel;

    public GoodPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mGoodModel = new GoodModel();
    }

    public void getGodos(int commentId) {
        mGoodModel.getGood(commentId, new HttpCallBack<GoodBean>() {
            @Override
            public void onSuccess(GoodBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
