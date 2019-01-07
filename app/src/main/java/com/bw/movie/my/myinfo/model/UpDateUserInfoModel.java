package com.bw.movie.my.myinfo.model;

import com.bw.movie.my.myinfo.bean.UpDateUserInfoEntity;
import com.bw.movie.my.myinfo.service.UpDateUserInfoService;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
*  修改用户信息的model层
* */
public class UpDateUserInfoModel  {

    public void getUserInfo(String nickName,int sex,String email,final getUserInterface getUserInterface){
        OkHttpUtil.get().createa(UpDateUserInfoService.class).getUserInfo(nickName,sex,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpDateUserInfoEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpDateUserInfoEntity upDateUserInfoEntity) {
                       getUserInterface.getUserInfo(upDateUserInfoEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //创建接口 传值
    public interface getUserInterface{
        void getUserInfo(UpDateUserInfoEntity upDateUserInfoEntity);
    }
}
