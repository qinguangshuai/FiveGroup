package com.bw.movie.my.myoption.model;
import com.bw.movie.my.myoption.bean.MyOptionEntity;
import com.bw.movie.my.myoption.service.MyOptionService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
   我的意见反馈model层
* */
public class MyOptionModel {
    public void getOpition(String content,final isMessage isMessage){
        OkHttpUtil.get().createa(MyOptionService.class).getOption(content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyOptionEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyOptionEntity myOptionEntity) {
                         //onNext方法中回调接口
                        isMessage.onOption(myOptionEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //创建接口
    public interface isMessage{
        void onOption(MyOptionEntity myOptionEntity);
    }
}
