package com.bw.movie.film.details.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.details.bean.CancelFollowMovieBean;
import com.bw.movie.film.details.model.CancelFollowModel;
import com.bw.movie.net.HttpCallBack;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class CancelFollowPresenter extends BasePresenter {

    private CancelFollowModel mCancelFollowModel;

    public CancelFollowPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mCancelFollowModel = new CancelFollowModel();
    }


    //取消关注
    public void getCancelFollowMovieBeanObservable(int can) {
        mCancelFollowModel.getCancelFollowMovieBeanObservable(can, new HttpCallBack<CancelFollowMovieBean>() {
            @Override
            public void onSuccess(CancelFollowMovieBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
