package com.bw.movie.my.mysound;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.net.HttpCallBack;

/**
 * date:2018/12/30    8:35
 * author:Therefore(Lenovo)
 * fileName:MySoundPresenter
 */
public class XiSoundPresenter extends BasePresenter {

    private XiSoundModel mXiSoundModel;

    public XiSoundPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mXiSoundModel = new XiSoundModel();
    }

    public void getSound() {
        mXiSoundModel.getSound(new HttpCallBack<XiSoundUser>() {
            @Override
            public void onSuccess(XiSoundUser name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
