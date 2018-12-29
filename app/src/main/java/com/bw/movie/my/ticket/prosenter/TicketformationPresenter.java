package com.bw.movie.my.ticket.prosenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.model.TicketformationModel;
import com.bw.movie.my.ticket.view.TicketformationView;

/*
* 购票记录p层
* */
public class TicketformationPresenter extends BasePresenter<TicketformationView> {
    private TicketformationModel model;

    public TicketformationPresenter(TicketformationView iBaseView) {
        super(iBaseView);
        model=new TicketformationModel();
    }

    public void getTicet(int page,int count){
      model.getMeassage(page, count, new TicketformationModel.getMessage() {
          @Override
          public void getMessage(TicketFoemationEntity ticketFoemationEntity) {
              getiBaseView().onDataSuccess(ticketFoemationEntity);
          }
      });
    }
}
