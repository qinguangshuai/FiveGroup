package com.bw.movie.my.ticket.ticketnet.model;

import com.bw.movie.my.ticket.ticketnet.bean.TicketBean;
import com.bw.movie.my.ticket.ticketnet.service.TicketService;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TicketModel  {
    public void getTicket(int scheduleId, int amount, String sign, final getTicket getTicket){
        OkHttpUtil.get().createa(TicketService.class).getTicket(scheduleId, amount, sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TicketBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TicketBean ticketBean) {
                        getTicket.getTickets(ticketBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface getTicket{
        void getTickets(TicketBean ticketBean);
    }
}
