package com.bw.movie.login.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.login.bean.XinUser;
import com.bw.movie.login.model.XinModel;
import com.bw.movie.login.view.XinView;
import com.bw.movie.net.HttpCallBack;

/**
 * date:2018/12/26    8:25
 * author:Therefore(Lenovo)
 * fileName:LoginPresenter
 */
public class XinPresenter extends BasePresenter<XinView> {

    private XinModel mXinModel;

    public XinPresenter(XinView iBaseView) {
        super(iBaseView);
        mXinModel = new XinModel();
    }


    public void getLogin(String token, int os) {
        mXinModel.getXinGe(token, os, new HttpCallBack<XinUser>() {
            @Override
            public void onSuccess(XinUser name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
