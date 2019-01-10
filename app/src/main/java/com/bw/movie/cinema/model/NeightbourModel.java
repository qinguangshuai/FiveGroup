package com.bw.movie.cinema.model;


import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.bean.neightbourbean.NeightbourBean;
import com.bw.movie.cinema.service.NeighbourService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NeightbourModel extends BaseModel {
    public void getNeightbour(int page, int count, final HttpCallBack<NeightbourBean> httpCallBack) {
        OkHttpUtil.get().createa(NeighbourService.class).getNeightbour(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<NeightbourBean>(httpCallBack));
    }

}
