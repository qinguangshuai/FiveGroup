package com.bw.movie.cinema.cannelfollow.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.cannelfollow.model.CannelFollowModel;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.net.HttpCallBack;

/**
 * date:2018/12/27    17:59
 * author:张文龙(张文龙)
 * fileName:CannelFollowPresenter
 */
public class CannelFollowPresenter extends BasePresenter {

    private CannelFollowModel mCannelFollowModel;

    public CannelFollowPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mCannelFollowModel = new CannelFollowModel();
    }

    public void getCannelFollow(int cinemaId) {
        mCannelFollowModel.getCannelFollow(cinemaId, new HttpCallBack<FollowBean>() {
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
