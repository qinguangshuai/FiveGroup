package com.bw.movie.my.myinfo.prosenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.myinfo.bean.UpDateUserInfoEntity;
import com.bw.movie.my.myinfo.model.UpDateUserInfoModel;
import com.bw.movie.my.myinfo.view.UpDateUserInfoView;
import com.bw.movie.net.HttpCallBack;

/*
 *  修改用户信息presenter层
 * */
public class UpDateUserInfoPresenter extends BasePresenter<UpDateUserInfoView> {
    private UpDateUserInfoModel model;

    public UpDateUserInfoPresenter(UpDateUserInfoView iBaseView) {
        super(iBaseView);
        model = new UpDateUserInfoModel();
    }

    public void getUserInfo(String nickName, int sex, String email) {
        model.getUserInfo(nickName, sex, email, new HttpCallBack<UpDateUserInfoEntity>() {
            @Override
            public void onSuccess(UpDateUserInfoEntity name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
