package com.bw.movie.my.attention.model;

import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attcinema.service.AttCinemaService;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.my.attention.service.AttFilmService;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/28    15:11
 * author:Therefore(Lenovo)
 * fileName:AttCinemaModel
 */
public class AttFilmModel {
    public void getFilm(int page, final HttpCinema httpCinema){
        OkHttpUtil.get().createa(AttFilmService.class).getFilm(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyAttFilmUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyAttFilmUser myAttFilmUser) {
                        httpCinema.getSuccess(myAttFilmUser);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface HttpCinema{
        void getSuccess(MyAttFilmUser attCinemaUser);
    }
}
