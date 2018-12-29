package com.bw.movie.my.attention.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attcinema.model.AttCinemaModel;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.my.attention.model.AttFilmModel;

/**
 * date:2018/12/28    15:22
 * author:Therefore(Lenovo)
 * fileName:AttCinemaPresenter
 */
public class AttFilmPresenter extends BasePresenter {

    private AttFilmModel mAttFilmModel;

    public AttFilmPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mAttFilmModel = new AttFilmModel();
    }

    public void getFilm(int page){
        mAttFilmModel.getFilm(page, new AttFilmModel.HttpCinema() {
            @Override
            public void getSuccess(MyAttFilmUser attCinemaUser) {
                getiBaseView().onDataSuccess(attCinemaUser);
            }
        });
    }
}
