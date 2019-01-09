package com.bw.movie.my.ticket.model;

import com.bw.movie.base.BaseModel;
import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.service.TicketFormationService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.SpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *  购票记录model
 * */
public class TicketformationModel extends BaseModel {

    public void getMeassage(int page, int count, final HttpCallBack<TicketFoemationEntity> httpCallBack) {
        OkHttpUtil.get().createa(TicketFormationService.class)
                .getTicket(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TicketFoemationEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TicketFoemationEntity ticketFoemationEntity) {
                        httpCallBack.onSuccess(ticketFoemationEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        httpCallBack.onFailer("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
