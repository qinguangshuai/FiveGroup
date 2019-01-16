package com.bw.movie.cinema.Particulars;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.net.HttpCallBack;

/**
 * 影院内容轮播Presenter
 */
public class ParticularsPresenter extends BasePresenter {
    private ParticularsModel mParticularsModel;

    public ParticularsPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mParticularsModel = new ParticularsModel();
    }

    public void getParticulars(int page, int count) {
        mParticularsModel.getParticulars(page, count, new HttpCallBack<ParticularsBean>() {
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
