package com.bw.movie.login.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.login.model.LoginModel;
import com.bw.movie.login.view.LoginView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/26    8:25
 * author:Therefore(Lenovo)
 * fileName:LoginPresenter
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel loginModel;

    public LoginPresenter(LoginView iBaseView) {
        super(iBaseView);
        loginModel = new LoginModel();
    }

    public void getLogin(String phone, String pwd) {
          loginModel.getLogin(phone, pwd, new LoginModel.isLogin() {
              @Override
              public void thisLogin(LoginUser loginUser) {
                    getiBaseView().onDataSuccess(loginUser);
              }
          });
    }
}
