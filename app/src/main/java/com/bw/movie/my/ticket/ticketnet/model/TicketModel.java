package com.bw.movie.my.ticket.ticketnet.model;

import android.content.Intent;
import android.os.Handler;

import com.bw.movie.MyApp;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.ticket.ticketnet.bean.TicketBean;
import com.bw.movie.my.ticket.ticketnet.service.TicketService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TicketModel extends BaseModel {
    public void getTicket(int scheduleId, int amount, String sign, HttpCallBack<TicketBean> httpCallBack){
        OkHttpUtil.get().createa(TicketService.class).getTicket(scheduleId, amount, sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TicketBean>(httpCallBack){
                    @Override
                    public void onNext(TicketBean ticketBean) {
                        if (ticketBean.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /*MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();*/
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 100);
                        } else {
                            super.onNext(ticketBean);
                        }
                    }
                });
    }
}
