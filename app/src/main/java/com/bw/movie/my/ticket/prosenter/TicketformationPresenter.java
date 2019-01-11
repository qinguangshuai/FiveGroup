package com.bw.movie.my.ticket.prosenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.model.TicketformationModel;
import com.bw.movie.my.ticket.view.TicketformationView;
import com.bw.movie.util.HttpCallBack;

/*
 * 购票记录p层
 * */
public class TicketformationPresenter extends BasePresenter {
    private TicketformationModel model;

    public TicketformationPresenter(IBaseView iBaseView) {
        super(iBaseView);
        model = new TicketformationModel();
    }

    public void getTicet(int page, int count) {
        model.getMeassage(page, count, new HttpCallBack<TicketFoemationEntity>() {
            @Override
            public void onSuccess(TicketFoemationEntity name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
