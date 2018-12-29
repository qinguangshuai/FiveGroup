package com.bw.movie.cinema.Particulars;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;

/**
 * 影院内容轮播Presenter
 */
public class ParticularsPresenter extends BasePresenter {
    private ParticularsModel particularsModel;
    public ParticularsPresenter(IBaseView iBaseView) {
        super(iBaseView);
        particularsModel = new ParticularsModel();
    }
    public void getParticulars(int page,int count){
        particularsModel.getParticulars(page, count, new ParticularsModel.isParticulars() {
            @Override
            public void getParticulars(ParticularsBean particularsBean) {
                 getiBaseView().onDataSuccess(particularsBean);
            }
        });
    }
}
