package com.bw.movie.cinema.Particulars;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.util.HttpCallBack;

/**
 * 影院内容轮播Presenter
 */
public class ParticularsPresenter extends BasePresenter {
    private ParticularsModel particularsModel;

    public ParticularsPresenter(IBaseView iBaseView) {
        super(iBaseView);
        particularsModel = new ParticularsModel();
    }

    public void getParticulars(int page, int count) {
        particularsModel.getParticulars(page, count, new HttpCallBack<ParticularsBean>() {
            @Override
            public void onSuccess(ParticularsBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
