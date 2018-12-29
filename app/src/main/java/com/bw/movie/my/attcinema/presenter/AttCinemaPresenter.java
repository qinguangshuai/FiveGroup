package com.bw.movie.my.attcinema.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attcinema.model.AttCinemaModel;

/**
 * date:2018/12/28    15:22
 * author:Therefore(Lenovo)
 * fileName:AttCinemaPresenter
 */
public class AttCinemaPresenter extends BasePresenter {

    private AttCinemaModel mAttCinemaModel;

    public AttCinemaPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mAttCinemaModel = new AttCinemaModel();
    }

    public void getCinema(int page){
        mAttCinemaModel.getCinema(page, new AttCinemaModel.HttpCinema() {
            @Override
            public void getSuccess(AttCinemaUser attCinemaUser) {
                getiBaseView().onDataSuccess(attCinemaUser);
            }
        });
    }
}
