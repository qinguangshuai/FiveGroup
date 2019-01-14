package com.bw.movie.registe.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.registe.bean.RegisteUser;
import com.bw.movie.registe.model.RegisteModel;
import com.bw.movie.registe.view.RegisteView;
import com.bw.movie.net.HttpCallBack;

/**
 * date:2018/12/26    18:49
 * author:Therefore(Lenovo)
 * fileName:RegistePresenter
 */
public class RegistePresenter extends BasePresenter<RegisteView> {

    private RegisteModel mRegisteModel;

    public RegistePresenter(RegisteView iBaseView) {
        super(iBaseView);
        mRegisteModel = new RegisteModel();
    }

    public void postRegiste(String nickName, String phone, String pwd, String pwd2, int sex, String birthday, String imei, String ua, String screenSize, String os, String email) {
        mRegisteModel.postRegiste(nickName, phone, pwd, pwd2, sex, birthday, imei, ua, screenSize, os, email, new HttpCallBack<RegisteUser>() {
                    @Override
                    public void onSuccess(RegisteUser name) {
                        getiBaseView().onDataSuccess(name);
                    }

                    @Override
                    public void onFailer(String result) {
                        getiBaseView().onDataFailer(result);
                    }
                }
        );
    }
}
