package com.bw.movie.cinema.follow.model;

import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.cinema.follow.service.FollowService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/27    17:29
 * author:张文龙(张文龙)
 * fileName:FollowModel
 */
public class FollowModel {

    public void getFollow(int cinemaId, final isFollow isFollow) {
        OkHttpUtil.get().createa(FollowService.class).getFollow(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowBean followBean) {
                        isFollow.getFollow(followBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface isFollow {
        void getFollow(FollowBean followBean);
    }
}
