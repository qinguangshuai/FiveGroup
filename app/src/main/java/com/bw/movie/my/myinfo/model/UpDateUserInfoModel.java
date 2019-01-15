package com.bw.movie.my.myinfo.model;

import android.os.Handler;

import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.my.myinfo.bean.UpDateUserInfoEntity;
import com.bw.movie.my.myinfo.service.UpDateUserInfoService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
*  修改用户信息的model层
* */
public class UpDateUserInfoModel extends BaseModel {

    public void getUserInfo(String nickName,int sex,String email,final HttpCallBack<UpDateUserInfoEntity> httpCallBack){
        OkHttpUtil.get().createa(UpDateUserInfoService.class).getUserInfo(nickName,sex,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UpDateUserInfoEntity>(httpCallBack){
                    @Override
                    public void onNext(UpDateUserInfoEntity upDateUserInfoEntity) {
                        if (upDateUserInfoEntity.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 100);
                        } else {
                            super.onNext(upDateUserInfoEntity);
                        }
                    }
                });
    }


}
