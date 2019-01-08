package com.bw.movie.my.mylatest.prosenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.mylatest.bean.MyLatestUser;
import com.bw.movie.my.mylatest.model.MyLatestModel;
import com.bw.movie.util.HttpCallBack;

/**
 * date:2018/12/27    19:44
 * author:Therefore(Lenovo)
 * fileName:MyLatestPresenter
 */
public class MyLatestPresenter extends BasePresenter {

    private MyLatestModel mMyLatestModel;

    public MyLatestPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mMyLatestModel = new MyLatestModel();
    }

    public void getVersion() {
        mMyLatestModel.getVersion(new HttpCallBack<MyLatestUser>() {
            @Override
            public void onSuccess(MyLatestUser name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
