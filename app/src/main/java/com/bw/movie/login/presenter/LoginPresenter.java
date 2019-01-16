package com.bw.movie.login.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.login.model.LoginModel;
import com.bw.movie.login.view.LoginView;
import com.bw.movie.net.HttpCallBack;

/**
 * date:2018/12/26    8:25
 * author:Therefore(Lenovo)
 * fileName:LoginPresenter
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel mLoginModel;

    public LoginPresenter(LoginView iBaseView) {
        super(iBaseView);
        mLoginModel = new LoginModel();
    }

    public void getLogin(String phone, String pwd) {
        mLoginModel.getLogin(phone, pwd, new HttpCallBack<LoginUser>() {
            @Override
            public void onSuccess(LoginUser name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
