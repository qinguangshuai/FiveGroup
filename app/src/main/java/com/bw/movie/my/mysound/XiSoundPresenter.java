package com.bw.movie.my.mysound;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;

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

    public void getSound(){
        mXiSoundModel.getSound(new XiSoundModel.HttpXi() {
            @Override
            public void getSuccess(XiSoundUser xiSoundUser) {
                getiBaseView().onDataSuccess(xiSoundUser);
            }
        });
    }
}
