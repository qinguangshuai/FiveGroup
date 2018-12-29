package com.bw.movie.cinema.mevaluate.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.mevaluate.bean.MevaluateBean;
import com.bw.movie.cinema.mevaluate.model.MevaluateModel;

/**
 * date:2018/12/29    8:31
 * author:张文龙(张文龙)
 * fileName:MevaluatePresenter
 */
public class MevaluatePresenter extends BasePresenter {
    private MevaluateModel mevaluateModel ;
    public MevaluatePresenter(IBaseView iBaseView) {
        super(iBaseView);
        mevaluateModel = new MevaluateModel();
    }
    public void getMevaluate(int cinemaId,int page,int count){
        mevaluateModel.getMevaluate(cinemaId, page, count, new MevaluateModel.IsMevaluate() {
            @Override
            public void getModevalute(MevaluateBean mevaluateBean) {
                getiBaseView().onDataSuccess(mevaluateBean);
            }
        });
    }
}
