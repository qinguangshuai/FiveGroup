package com.bw.movie.cinema.search.model;

import com.bw.movie.cinema.search.bean.SearchBean;
import com.bw.movie.cinema.search.service.SearchService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/*
*影院搜索页面(model)
 */
public class SearchModel {
    public void getSearch(int page, int count, String cinemaName, final getSreach getSreach){
       OkHttpUtil.get().createa(SearchService.class).getSearch(page, count, cinemaName)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<SearchBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(SearchBean searchBean) {
                    getSreach.getSreachs(searchBean);
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }
    public interface  getSreach{
        void getSreachs(SearchBean searchBean);
    }
}
