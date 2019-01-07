package com.bw.movie.cinema.follow.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.cinema.follow.model.FollowModel;

/**
 * date:2018/12/27    17:29
 * author:张文龙(张文龙)
 * fileName:FollowPresenter
 */
public class FollowProsenter extends BasePresenter {
    private FollowModel followModel;
    public FollowProsenter(IBaseView iBaseView) {
        super(iBaseView);
        followModel=new FollowModel();


    }
    public void getFollow(int cinemaId){
        followModel.getFollow(cinemaId, new FollowModel.isFollow() {
            @Override
            public void getFollow(FollowBean followBean) {
                getiBaseView().onDataSuccess(followBean);
            }
        });
    }
}
