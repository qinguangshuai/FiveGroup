package com.bw.movie.login.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.login.bean.XinUser;
import com.bw.movie.login.model.LoginModel;
import com.bw.movie.login.model.XinModel;
import com.bw.movie.login.view.LoginView;
import com.bw.movie.login.view.XinView;

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
          mXinModel.getXinGe(token, os, new XinModel.HttpXinGe() {
              @Override
              public void thisLogin(XinUser xinUser) {
                  getiBaseView().onDataSuccess(xinUser);
              }
          });
    }
}
