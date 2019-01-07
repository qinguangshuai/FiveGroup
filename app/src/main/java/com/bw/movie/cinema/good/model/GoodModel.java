package com.bw.movie.cinema.good.model;

import com.bw.movie.cinema.good.bean.GoodBean;
import com.bw.movie.cinema.good.service.GoodService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 评论点赞(model)
 */
public class GoodModel {
    public void getGood(int commentId, final getGods getGods){
        OkHttpUtil.get().createa(GoodService.class).getGoods(commentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoodBean goodBean) {
                       getGods.isGoods(goodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface getGods{
        void isGoods(GoodBean goodBean);
    }
}
