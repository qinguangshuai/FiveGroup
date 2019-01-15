package com.bw.movie.cinema.search.model;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.search.bean.SearchBean;
import com.bw.movie.cinema.search.service.SearchService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *影院搜索页面(model)
 */
public class SearchModel extends BaseModel {
    public void getSearch(int page, int count, String cinemaName, final HttpCallBack<SearchBean> httpCallBack) {
        OkHttpUtil.get().createa(SearchService.class).getSearch(page, count, cinemaName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SearchBean>(httpCallBack));
    }
}
