package com.bw.movie.film.p;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.bean.CarouselBean;
import com.bw.movie.film.bean.CommentBean;
import com.bw.movie.film.bean.DetailBean;
import com.bw.movie.film.bean.HotPlayBean;
import com.bw.movie.film.bean.PlayingBean;
import com.bw.movie.film.bean.PopularBean;
import com.bw.movie.film.m.CarouseCallBack;
import com.bw.movie.film.m.CommentCallBack;
import com.bw.movie.film.m.DetailCallBack;
import com.bw.movie.film.m.FilmModle;
import com.bw.movie.film.m.HotPlayCallBack;
import com.bw.movie.film.m.PlayingCallBack;
import com.bw.movie.film.m.PopularCallBack;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class FilmProsenter extends BasePresenter {


    private FilmModle mFilmModle;

    public FilmProsenter(IBaseView iBaseView) {
        super(iBaseView);
        mFilmModle = new FilmModle();
    }

    //轮播图请求回调
    public void getCarouselBeanObservable(int page, int count){
        mFilmModle.getCarouselBeanObservable(page, count, new CarouseCallBack() {
            @Override
            public void success(CarouselBean carouselBean) {
                getiBaseView().onDataSuccess(carouselBean);
            }
            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }


    //热门电影请求回调
    public void getPopularBeanObservable(int page, int count){
        mFilmModle.getPopularBeanObservable(page, count, new PopularCallBack() {
            @Override
            public void success(PopularBean popularBean) {
                getiBaseView().onDataSuccess(popularBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }

    //正在热映 请求回调
    public void getHotPlayBeanObservable(int page, int count){
        mFilmModle.getHotPlayBeanObservable(page, count, new HotPlayCallBack() {
            @Override
            public void success(HotPlayBean hotPlayBean) {
                getiBaseView().onDataSuccess(hotPlayBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }


    //正在上映 || 即将上映 请求 回调
    public void getPlayingBeanObservable(int page, int count){
        mFilmModle.getPlayingBeanObservable(page, count, new PlayingCallBack() {
            @Override
            public void success(PlayingBean playingBean) {
                getiBaseView().onDataSuccess(playingBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }


    //Id查询详情
    public void getDetailBeanObservable(int id){
        mFilmModle.getDetailBeanObservable(id, new DetailCallBack() {
            @Override
            public void success(DetailBean detailBean) {
                getiBaseView().onDataSuccess(detailBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }


    //评论
    public void getCommentBeanObservable(int id , int page ,int count){
        mFilmModle.getCommentBeanObservable(id, page, count, new CommentCallBack() {
            @Override
            public void success(CommentBean commentBean) {
                getiBaseView().onDataSuccess(commentBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }


}
