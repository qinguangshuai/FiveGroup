package com.bw.movie.login.model;

import com.bw.movie.Constant;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.login.service.LoginService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.SpUtil;
import com.bw.movie.util.ToastUtil;

import java.lang.reflect.Method;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/26    8:25
 * author:Therefore(Lenovo)
 * fileName:LoginModel
 */
public class LoginModel {
    public void getLogin(String phone, String pwd, final HttpCallBack<LoginUser> httpCallBack) {
        OkHttpUtil.get().createa(LoginService.class).getLogn(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginUser>(httpCallBack){
                    @Override
                    public void onNext(LoginUser loginUser) {
                        super.onNext(loginUser);
                        if (loginUser.getStatus().equals("0000")) {
                            SpUtil.put("userId", String.valueOf(loginUser.getResult().getUserId()));
                            SpUtil.put("birthday", loginUser.getResult().getUserInfo().getBirthday());
                            SpUtil.put(Constant.HEADPIC, loginUser.getResult().getUserInfo().getHeadPic());
                            SpUtil.put("lastLoginTime", loginUser.getResult().getUserInfo().getLastLoginTime());
                            SpUtil.put(Constant.NICKNAME, loginUser.getResult().getUserInfo().getNickName());
                            SpUtil.put(Constant.PHONE, loginUser.getResult().getUserInfo().getPhone());
                            SpUtil.put("id", loginUser.getResult().getUserInfo().getId());
                            SpUtil.put("sex", loginUser.getResult().getUserInfo().getSex());
                            SpUtil.put("message",loginUser.getMessage());
                            SpUtil.put(Constant.SESSIONId, loginUser.getResult().getSessionId());
                        }else {
                            ToastUtil.Toast("没数据");
                        }
                    }
                });
    }
}
