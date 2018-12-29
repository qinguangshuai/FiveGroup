package com.bw.movie.my.myinfo.updatepwd.model;

import com.bw.movie.my.myinfo.updatepwd.bean.UpdatePwdEntity;
import com.bw.movie.my.myinfo.updatepwd.service.UpdatePwdService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
   修改密码model层
* */
public class UpdatePwdModel {
    public void getPwd(String oldPwd, String newPwd, String newPwd2, final getPwd getPwd) {
        OkHttpUtil.get().createa(UpdatePwdService.class).getPwd(oldPwd, newPwd, newPwd2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdatePwdEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdatePwdEntity updatePwdEntity) {
                        getPwd.getPwd(updatePwdEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //创建接口
    public interface getPwd {
        void getPwd(UpdatePwdEntity updatePwdEntity);
    }
}
