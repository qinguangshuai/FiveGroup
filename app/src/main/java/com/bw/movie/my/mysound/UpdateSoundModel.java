package com.bw.movie.my.mysound;

import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/30    8:34
 * author:Therefore(Lenovo)
 * fileName:MySoundModel
 */
public class UpdateSoundModel {
    public void getSound(int id,final HttpUpdate httpUpdate){
        OkHttpUtil.get().createa(UpdateSoundService.class).getUpdate(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UpdateSoundUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateSoundUser updateSoundUser) {
                        httpUpdate.getSuccess(updateSoundUser);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface HttpUpdate{
        void getSuccess(UpdateSoundUser updateSoundUser);
    }
}
