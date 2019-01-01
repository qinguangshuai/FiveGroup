package com.bw.movie.my.mysound;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;

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

    public void getSound(int id){
        mUpdateSoundModel.getSound(id, new UpdateSoundModel.HttpUpdate() {
            @Override
            public void getSuccess(UpdateSoundUser updateSoundUser) {
                getiBaseView().onDataSuccess(updateSoundUser);
            }
        });
    }
}
