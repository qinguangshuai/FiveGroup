package com.bw.movie.my.ticket.model;

import android.content.Intent;
import android.os.Handler;

import com.bw.movie.MyApp;
import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.service.TicketFormationService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
                .subscribe(new BaseObserver<TicketFoemationEntity>(httpCallBack){
                    @Override
                    public void onNext(TicketFoemationEntity ticketFoemationEntity) {
                        if (ticketFoemationEntity.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MyApp.sContext.startActivity(new Intent(MyApp.sContext, LoginActivity.class));
                                    AppManager.getAppManager().finishAllActivity();
                                }
                            }, 100);
                        } else {
                            super.onNext(ticketFoemationEntity);
                        }
                    }
                });
    }
}
