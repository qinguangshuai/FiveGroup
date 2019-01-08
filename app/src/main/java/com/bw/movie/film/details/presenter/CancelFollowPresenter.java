package com.bw.movie.film.details.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.details.bean.CancelFollowMovieBean;
import com.bw.movie.film.details.callback.CancelFollowMovieCallBack;
import com.bw.movie.film.details.model.CancelFollowModel;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class CancelFollowPresenter extends BasePresenter {

    private CancelFollowModel cancelFollowModel;

    public CancelFollowPresenter(IBaseView iBaseView) {
        super(iBaseView);
        cancelFollowModel = new CancelFollowModel();
    }


    //取消关注
    public void getCancelFollowMovieBeanObservable(int can) {
        cancelFollowModel.getCancelFollowMovieBeanObservable(can, new CancelFollowMovieCallBack() {
            @Override
            public void success(CancelFollowMovieBean cancelFollowMovieBean) {
                getiBaseView().onDataSuccess(cancelFollowMovieBean);
            }

            @Override
            public void error(String s) {
                getiBaseView().onDataFailer(s);
            }
        });
    }
}
