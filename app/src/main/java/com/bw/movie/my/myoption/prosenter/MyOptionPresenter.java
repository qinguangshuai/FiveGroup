package com.bw.movie.my.myoption.prosenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.myoption.bean.MyOptionEntity;
import com.bw.movie.my.myoption.model.MyOptionModel;
import com.bw.movie.my.myoption.view.MyOpitionView;
import com.bw.movie.util.HttpCallBack;

/*
  我的意见presenter层
* */
public class MyOptionPresenter extends BasePresenter<MyOpitionView> {

    private MyOptionModel myOptionModel;

    public MyOptionPresenter(MyOpitionView iBaseView) {
        super(iBaseView);
        myOptionModel = new MyOptionModel();
    }

    public void getOption(String context) {
        myOptionModel.getOpition(context, new HttpCallBack<MyOptionEntity>() {
            @Override
            public void onSuccess(MyOptionEntity name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
