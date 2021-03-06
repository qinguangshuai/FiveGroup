package com.bw.movie.my.mysound;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.net.HttpCallBack;

/**
 * date:2018/12/30    8:35
 * author:Therefore(Lenovo)
 * fileName:MySoundPresenter
 */
public class MySoundPresenter extends BasePresenter {

    private MySoundModel mMySoundModel;

    public MySoundPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mMySoundModel = new MySoundModel();
    }

    public void getSound(int page) {
        mMySoundModel.getSound(page, new HttpCallBack<MySoundUser>() {
            @Override
            public void onSuccess(MySoundUser name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }

}
