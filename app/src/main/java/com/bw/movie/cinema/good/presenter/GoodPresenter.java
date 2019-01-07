package com.bw.movie.cinema.good.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.good.bean.GoodBean;
import com.bw.movie.cinema.good.model.GoodModel;

public class GoodPresenter extends BasePresenter {
    private GoodModel goodModel;

    public GoodPresenter(IBaseView iBaseView) {
        super(iBaseView);
        goodModel = new GoodModel();
    }
    public void getGodos(int commentId){
        goodModel.getGood(commentId, new GoodModel.getGods() {
            @Override
            public void isGoods(GoodBean goodBean) {
                 getiBaseView().onDataSuccess(goodBean);
            }
        });
    }
}
