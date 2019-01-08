package com.bw.movie.my.mysound;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.util.HttpCallBack;

/**
 * date:2018/12/30    8:35
 * author:Therefore(Lenovo)
 * fileName:MySoundPresenter
 */
public class UpdateSoundPresenter extends BasePresenter {

    private UpdateSoundModel mUpdateSoundModel;

    public UpdateSoundPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mUpdateSoundModel = new UpdateSoundModel();
    }

    public void getSound(int id) {
        mUpdateSoundModel.getSound(id, new HttpCallBack<UpdateSoundUser>() {
            @Override
            public void onSuccess(UpdateSoundUser name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
