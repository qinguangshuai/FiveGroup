package com.bw.movie.cinema.prosenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.bean.neightbourbean.NeightbourBean;
import com.bw.movie.cinema.model.NeightbourModel;

/*
* NeightbourPresenter
* */
public class NeightbourPresenter extends BasePresenter {
     private NeightbourModel neightbourModel;
    public NeightbourPresenter(IBaseView iBaseView) {
        super(iBaseView);
        neightbourModel = new NeightbourModel();
    }
    public void getNeightbour(int page,int count){
           neightbourModel.getNeightbour(page, count, new NeightbourModel.isSuccess() {
               @Override
               public void getSuccess(NeightbourBean neightbourBean) {
                      getiBaseView().onDataSuccess(neightbourBean);
               }
           });
    }

}
