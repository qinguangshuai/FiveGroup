package com.bw.movie.my.ticket.ticketnet.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.ticket.ticketnet.bean.TicketBean;
import com.bw.movie.my.ticket.ticketnet.model.TicketModel;
import com.bw.movie.net.HttpCallBack;

public class TiketPresenter extends BasePresenter {

    private TicketModel mTicketModel;

    public TiketPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mTicketModel = new TicketModel();
    }
    public void getTicket(int scheduleId, int amount, String sign){
        mTicketModel.getTicket(scheduleId, amount, sign, new HttpCallBack<TicketBean>() {
            @Override
            public void onSuccess(TicketBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer("失败");
            }
        });
    }
}
