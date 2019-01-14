package com.bw.movie.cinema.Particulars.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.Particulars.bean.MovieListByCinemaIdBean;
import com.bw.movie.cinema.Particulars.model.MovieListByCinemaIdModel;
import com.bw.movie.net.HttpCallBack;

/**
 * date:2018/12/27    16:06
 * author:张文龙(张文龙)
 * fileName:MovieListByCinemaIdPresenter
 */
public class MovieListByCinemaIdPresenter extends BasePresenter {

    private MovieListByCinemaIdModel movieListByCinemaIdModel;

    public MovieListByCinemaIdPresenter(IBaseView iBaseView) {
        super(iBaseView);
        movieListByCinemaIdModel = new MovieListByCinemaIdModel();
    }

    public void getMovieByBean(int cinemaId) {
        movieListByCinemaIdModel.getMovieByBean(cinemaId, new HttpCallBack<MovieListByCinemaIdBean>() {
            @Override
            public void onSuccess(MovieListByCinemaIdBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
