package com.bw.movie.wxapi.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.wxapi.bean.WXUser;
import com.bw.movie.wxapi.model.WXModel;
import com.bw.movie.wxapi.view.WXView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/27    11:21
 * author:Therefore(Lenovo)
 * fileName:WXPresenter
 */
public class WXPresenter extends BasePresenter<WXView> {

    private WXModel mWXModel;

    public WXPresenter(WXView iBaseView) {
        super(iBaseView);
        mWXModel = new WXModel();
    }

    public void postWX(String code){
        mWXModel.postWX(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WXUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WXUser wxUser) {
                        getiBaseView().onDataSuccess(wxUser);
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
