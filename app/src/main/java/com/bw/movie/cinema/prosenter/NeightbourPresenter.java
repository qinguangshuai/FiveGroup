package com.bw.movie.cinema.prosenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.bean.neightbourbean.NeightbourBean;
import com.bw.movie.cinema.model.NeightbourModel;
import com.bw.movie.util.HttpCallBack;

/*
 * NeightbourPresenter
 * */
public class NeightbourPresenter extends BasePresenter {
    private NeightbourModel neightbourModel;

    public NeightbourPresenter(IBaseView iBaseView) {
        super(iBaseView);
        neightbourModel = new NeightbourModel();
    }

    public void getNeightbour(int page, int count) {
        neightbourModel.getNeightbour(page, count, new HttpCallBack<NeightbourBean>() {
            @Override
            public void onSuccess(NeightbourBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }

}
