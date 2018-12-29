package com.bw.movie.my.ticket.model;

import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.service.TicketFormationService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
*  购票记录model
* */
public class TicketformationModel {
    public void getMeassage(int page,int count,final getMessage getMessage){
        OkHttpUtil.get().createa(TicketFormationService.class)
                .getTicket(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TicketFoemationEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TicketFoemationEntity ticketFoemationEntity) {
                        getMessage.getMessage(ticketFoemationEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface getMessage{
        void getMessage(TicketFoemationEntity ticketFoemationEntity);
    }
}
