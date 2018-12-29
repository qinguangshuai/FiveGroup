package com.bw.movie.my.myinfo.updatepwd.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.myinfo.updatepwd.bean.UpdatePwdEntity;
import com.bw.movie.my.myinfo.updatepwd.model.UpdatePwdModel;
import com.bw.movie.my.myinfo.updatepwd.view.UpdatePwdView;

/*
   修改密码presenter
* */
public class UpdatePwdPresenter extends BasePresenter<UpdatePwdView> {
    private UpdatePwdModel model;
    public UpdatePwdPresenter(UpdatePwdView iBaseView) {
        super(iBaseView);
        model=new UpdatePwdModel();
    }

    public void getPwd(String oldPwd, String newPwd, String newPwd2){
        model.getPwd(oldPwd, newPwd, newPwd2, new UpdatePwdModel.getPwd() {
            @Override
            public void getPwd(UpdatePwdEntity updatePwdEntity) {
                getiBaseView().onDataSuccess(updatePwdEntity);
            }
        });
    }
}
