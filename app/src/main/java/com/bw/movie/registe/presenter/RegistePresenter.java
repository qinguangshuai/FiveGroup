package com.bw.movie.registe.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.registe.bean.RegisteUser;
import com.bw.movie.registe.model.RegisteModel;
import com.bw.movie.registe.view.RegisteView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        mRegisteModel.postRegiste(nickName, phone, pwd, pwd2, sex, birthday, imei, ua, screenSize, os, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisteUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisteUser registeUser) {
                        getiBaseView().onDataSuccess(registeUser);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
