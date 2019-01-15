package com.bw.movie.cinema.follow.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.cinema.follow.model.FollowModel;
import com.bw.movie.net.HttpCallBack;

/**
 * date:2018/12/27    17:29
 * author:张文龙(张文龙)
 * fileName:FollowPresenter
 */
public class FollowProsenter extends BasePresenter {
    private FollowModel mFollowModel;

    public FollowProsenter(IBaseView iBaseView) {
        super(iBaseView);
        mFollowModel = new FollowModel();
    }

    public void getFollow(int cinemaId) {
        mFollowModel.getFollow(cinemaId, new HttpCallBack<FollowBean>() {
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
