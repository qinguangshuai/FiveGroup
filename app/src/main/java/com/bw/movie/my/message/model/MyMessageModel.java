package com.bw.movie.my.message.model;



import android.widget.Toast;

import com.bw.movie.my.message.bean.MyMessageEntity;
import com.bw.movie.my.message.service.MyMessageService;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/*
MyMessageModel
* */
public class MyMessageModel {

    public void getMessage(final isMessage isMessage){
        OkHttpUtil.get().createa(MyMessageService.class).getMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyMessageEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyMessageEntity myMessageEntity) {
                       isMessage.thisMessage(myMessageEntity);
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

    public interface isMessage{
        void thisMessage(MyMessageEntity myMessageEntity);
    }
}
