package com.bw.movie.film.details.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.details.model.FollowModel;
import com.bw.movie.film.details.bean.FollowBean;
import com.bw.movie.net.HttpCallBack;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class FollowPresenter extends BasePresenter {

    private FollowModel mFollowModel;

    public FollowPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mFollowModel = new FollowModel();
    }

    //关注
    public void getFollowBeanObservable(int folllow) {
        mFollowModel.getFollowBeanObservable(folllow, new HttpCallBack<FollowBean>() {
            @Override
            public void onSuccess(FollowBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }

}
