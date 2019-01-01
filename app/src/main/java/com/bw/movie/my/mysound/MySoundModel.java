package com.bw.movie.my.mysound;

import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/30    8:34
 * author:Therefore(Lenovo)
 * fileName:MySoundModel
 */
public class MySoundModel {
    public void getSound(int page,final HttpSound httpSound){
        OkHttpUtil.get().createa(MySoundService.class).getSound(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MySoundUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MySoundUser mySoundUser) {
                        httpSound.getSuccess(mySoundUser);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface HttpSound{
        void getSuccess(MySoundUser mySoundUser);
    }
}
