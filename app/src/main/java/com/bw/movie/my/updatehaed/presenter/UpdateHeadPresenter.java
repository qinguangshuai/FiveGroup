package com.bw.movie.my.updatehaed.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.updatehaed.bean.UpdateHeadEntity;
import com.bw.movie.my.updatehaed.model.UpdateHeadModel;
import com.bw.movie.my.updatehaed.view.UpdateHeadView;

import java.io.File;
/*
*  修改头像p层
* */
public class UpdateHeadPresenter extends BasePresenter<UpdateHeadView> {
    private UpdateHeadModel model;
    public UpdateHeadPresenter(UpdateHeadView iBaseView) {
        super(iBaseView);
        model=new UpdateHeadModel();
    }

    public void getHead(File file){
        model.getHead(file, new UpdateHeadModel.getHead() {
            @Override
            public void getHead(UpdateHeadEntity updateHeadEntity) {
                getiBaseView().onDataSuccess(updateHeadEntity);
            }
        });
    }
}
