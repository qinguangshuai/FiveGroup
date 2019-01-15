package com.bw.movie.my.myinfo.updatepwd.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.myinfo.updatepwd.bean.UpdatePwdEntity;
import com.bw.movie.my.myinfo.updatepwd.model.UpdatePwdModel;
import com.bw.movie.my.myinfo.updatepwd.view.UpdatePwdView;
import com.bw.movie.net.HttpCallBack;

/*
   修改密码presenter
* */
public class UpdatePwdPresenter extends BasePresenter<UpdatePwdView> {
    private UpdatePwdModel mModel;
    public UpdatePwdPresenter(UpdatePwdView iBaseView) {
        super(iBaseView);
        mModel =new UpdatePwdModel();
    }

    public void getPwd(String oldPwd, String newPwd, String newPwd2){
        mModel.getPwd(oldPwd, newPwd, newPwd2, new HttpCallBack<UpdatePwdEntity>() {
            @Override
            public void onSuccess(UpdatePwdEntity name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer("失败");
            }
        });
    }
}
