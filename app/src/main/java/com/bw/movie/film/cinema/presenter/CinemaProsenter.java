package com.bw.movie.film.cinema.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.cinema.bean.CinemaBean;
import com.bw.movie.film.cinema.callback.CinemaCallBack;
import com.bw.movie.film.cinema.model.CinemaModle;

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
    public void getCinemaBeanObservable(int id){
        cinemaModle.getCinemaBeanObservable(id, new CinemaCallBack() {
            @Override
            public void success(CinemaBean cinemaBean) {
                getiBaseView().onDataSuccess(cinemaBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }




}
