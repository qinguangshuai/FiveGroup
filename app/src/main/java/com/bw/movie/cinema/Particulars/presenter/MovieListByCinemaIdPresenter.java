package com.bw.movie.cinema.Particulars.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.Particulars.bean.MovieListByCinemaIdBean;
import com.bw.movie.cinema.Particulars.model.MovieListByCinemaIdModel;

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
        movieListByCinemaIdModel.getMovieByBean(cinemaId, new MovieListByCinemaIdModel.isMovieByBean() {
            @Override
            public void getMovieByBean(MovieListByCinemaIdBean movieListByCinemaIdBean) {
                getiBaseView().onDataSuccess(movieListByCinemaIdBean);
            }
        });
    }
}
