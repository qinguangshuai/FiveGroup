package com.bw.movie.cinema.mdetails.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.mdetails.bean.MdetailsBean;
import com.bw.movie.cinema.mdetails.model.MdetailsModel;

/**
 * date:2018/12/28    18:33
 * author:张文龙(张文龙)
 * fileName:MdetailsPresenter(影院详情)
 */
public class MdetailsPresenter extends BasePresenter {

    private MdetailsModel mdetailsModel;

    public MdetailsPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mdetailsModel = new MdetailsModel();
    }
    public void getMdetails(int cinemaId){
        mdetailsModel.getMdetails(cinemaId, new MdetailsModel.isMdetails() {
            @Override
            public void getMdetails(MdetailsBean mdetailsBean) {
                getiBaseView().onDataSuccess(mdetailsBean);
            }
        });
    }
}
