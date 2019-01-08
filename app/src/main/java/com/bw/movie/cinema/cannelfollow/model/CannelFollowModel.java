package com.bw.movie.cinema.cannelfollow.model;

import com.bw.movie.cinema.cannelfollow.service.CannelFollowService;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/27    17:59
 * author:张文龙(张文龙)
 * fileName:CannelFollowModel
 */
public class CannelFollowModel {

    public void getCannelFollow(int cinemaId, final isCannelFollow isCannelFollow) {
        OkHttpUtil.get().createa(CannelFollowService.class)
                .getCannelFollow(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowBean followBean) {
                        isCannelFollow.getCannelFollw(followBean);
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

    public interface isCannelFollow {
        void getCannelFollw(FollowBean followBean);
    }
}
