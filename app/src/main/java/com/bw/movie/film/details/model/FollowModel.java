package com.bw.movie.film.details.model;

import com.bw.movie.film.details.bean.FollowBean;
import com.bw.movie.film.details.callback.FollowCallBack;
import com.bw.movie.film.details.service.FollowService;
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
public class FollowModel {

    //关注
    public void getFollowBeanObservable(int follow, final FollowCallBack followCallBack){
        OkHttpUtil
                .get()
                .createa(FollowService.class)
                .getFollowBeanObservable(follow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowBean followBean) {
                       followCallBack.success(followBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        followCallBack.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }





}


