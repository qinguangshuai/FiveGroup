package com.bw.movie.my.attention.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.my.attention.model.AttFilmModel;
import com.bw.movie.net.HttpCallBack;

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

    public void getFilm(int page) {
        mAttFilmModel.getFilm(page, new HttpCallBack<MyAttFilmUser>() {
            @Override
            public void onSuccess(MyAttFilmUser name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
