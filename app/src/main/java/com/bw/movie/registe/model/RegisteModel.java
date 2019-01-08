package com.bw.movie.registe.model;

import com.bw.movie.my.mysound.MySoundModel;
import com.bw.movie.my.mysound.MySoundService;
import com.bw.movie.my.mysound.MySoundUser;
import com.bw.movie.registe.bean.RegisteUser;
import com.bw.movie.registe.service.RegisteSrevice;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/26    18:40
 * author:Therefore(Lenovo)
 * fileName:RegisteModel
 */
public class RegisteModel {
    /*public Observable<RegisteUser> postRegiste(String nickName,String phone,String pwd,String pwd2,int sex,String birthday,String imei,String ua,String screenSize,String os,String email){
        return OkHttpUtil.get().createa(RegisteSrevice.class).postRegiste(nickName, phone, pwd, pwd2, sex, birthday, imei, ua, screenSize, os, email);
    }*/

    public void postRegiste(String nickName,String phone,String pwd,String pwd2,int sex,String birthday,String imei,String ua,String screenSize,String os,String email,final HttpSound httpSound){
        OkHttpUtil.get().createa(RegisteSrevice.class).postRegiste(nickName, phone, pwd, pwd2, sex, birthday, imei, ua, screenSize, os, email)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegisteUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisteUser registeUser) {
                        httpSound.getSuccess(registeUser);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface HttpSound{
        void getSuccess(RegisteUser registeUser);
    }
}
