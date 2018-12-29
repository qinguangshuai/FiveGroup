package com.bw.movie.film.m;

import android.util.Log;

import com.bw.movie.film.service.FilmService;
import com.bw.movie.film.bean.CarouselBean;
import com.bw.movie.film.bean.HotPlayBean;
import com.bw.movie.film.bean.PlayingBean;
import com.bw.movie.film.bean.PopularBean;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *作者:ash
 *TODO:
 *   model 层
 */
public class FilmModle {

    //轮播图请求数据回调
    public void getCarouselBeanObservable(int page, int count, final CarouseCallBack carouseCallBack) {
        OkHttpUtil
                .get()
                .createa(FilmService.class)
                .getCarouselBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CarouselBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CarouselBean carouselBean) {
                        carouseCallBack.success(carouselBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        carouseCallBack.error(e.getMessage());
                        Log.e("error", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    //热门电影 请求数据回调
    public void getPopularBeanObservable(int page, int count, final PopularCallBack popularCallBack) {
        OkHttpUtil
                .get()
                .createa(FilmService.class)
                .getPopularBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PopularBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PopularBean popularBean) {
                        popularCallBack.success(popularBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        popularCallBack.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    //正在热映 请求数据回调
    public void getHotPlayBeanObservable(int page, int count, final HotPlayCallBack hotPlayCallBack) {
        OkHttpUtil
                .get()
                .createa(FilmService.class)
                .getHotPlayBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotPlayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotPlayBean hotPlayBean) {
                        hotPlayCallBack.success(hotPlayBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hotPlayCallBack.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //正在热映 请求数据回调
    public void getPlayingBeanObservable(int page, int count, final PlayingCallBack playingCallBack){
        OkHttpUtil
                .get()
                .createa(FilmService.class)
                .getPlayingBeanObservable(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PlayingBean playingBean) {
                        playingCallBack.success(playingBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        playingCallBack.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}


