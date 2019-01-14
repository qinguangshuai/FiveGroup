package com.bw.movie.film.cinema.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.cinema.bean.CinemaBean;
import com.bw.movie.film.cinema.model.CinemaModle;
import com.bw.movie.net.HttpCallBack;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class CinemaProsenter extends BasePresenter {


    private CinemaModle cinemaModle;

    public CinemaProsenter(IBaseView iBaseView) {
        super(iBaseView);
        cinemaModle = new CinemaModle();
    }

    //查询影院
    public void getCinemaBeanObservable(int id) {
        cinemaModle.getCinemaBeanObservable(id, new HttpCallBack<CinemaBean>() {
            @Override
            public void onSuccess(CinemaBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
