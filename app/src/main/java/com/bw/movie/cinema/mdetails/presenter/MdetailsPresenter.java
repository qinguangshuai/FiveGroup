package com.bw.movie.cinema.mdetails.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.mdetails.bean.MdetailsBean;
import com.bw.movie.cinema.mdetails.model.MdetailsModel;
import com.bw.movie.net.HttpCallBack;

/**
 * date:2018/12/28    18:33
 * author:张文龙(张文龙)
 * fileName:MdetailsPresenter(影院详情)
 */
public class MdetailsPresenter extends BasePresenter {

    private MdetailsModel mDetailsModel;

    public MdetailsPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mDetailsModel = new MdetailsModel();
    }

    public void getMdetails(int cinemaId) {
        mDetailsModel.getMdetails(cinemaId, new HttpCallBack<MdetailsBean>() {
            @Override
            public void onSuccess(MdetailsBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
