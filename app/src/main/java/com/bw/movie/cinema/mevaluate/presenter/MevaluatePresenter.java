package com.bw.movie.cinema.mevaluate.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.mevaluate.bean.MevaluateBean;
import com.bw.movie.cinema.mevaluate.model.MevaluateModel;
import com.bw.movie.util.HttpCallBack;

/**
 * date:2018/12/29    8:31
 * author:张文龙(张文龙)
 * fileName:MevaluatePresenter
 */
public class MevaluatePresenter extends BasePresenter {
    private MevaluateModel mevaluateModel;

    public MevaluatePresenter(IBaseView iBaseView) {
        super(iBaseView);
        mevaluateModel = new MevaluateModel();
    }

    public void getMevaluate(int cinemaId, int page, int count) {
        mevaluateModel.getMevaluate(cinemaId, page, count, new HttpCallBack<MevaluateBean>() {
            @Override
            public void onSuccess(MevaluateBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
